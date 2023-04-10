package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.CPU;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class CPUScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> cpuCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("integratedGraphics", "Интегрированная графическая система");
        put("packaging", "Тип упаковки");
        put("coreClock", "Базовая тактовая частота, ГГц");
        put("boostCoreClock", "Максимальная тактовая частота с технологией Turbo Boost");
        put("coreCount", "Количество ядер");
        put("threadCount", "Количество потоков");
        put("cacheL1", "Объем кэша L1");
        put("cacheL2", "Объем кэша L2");
        put("cacheL3", "Объем кэша L3");
        put("tdp", "Тепловыделение, Вт");
        put("socket", "Сокет");
        put("maxMemory", "Максимальный объем ОЗУ");
        put("maxMemoryChannels", "Макс. число каналов памяти");
        put("supportedMemoryTypes", "Поддерживаемый тип оперативной памяти");
        put("pciExpressVersion", "Стандарт PCI Express");
        put("maxPciExpressChannels", "Стандарт PCI Express");
        put("criticalTemperature", "Критическая температура, °С");
        put("supportedOSes", "Поддержка ОС");
        put("lithography", "Техпроцесс, Нм");
    }};

    public List<CPU> scrapeCPUs() {
        List<CPU> cpus = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/processory", cpuCharacteristicMap)
                .stream()
                .peek(cpuMap -> {
                    String maxMemory = cpuMap.get("maxMemory");
                    if (maxMemory != null) {
                        cpuMap.put("maxMemory", maxMemory.split(" ")[0]); // example of maxMemory: 128 Гб
                    }

                    String integratedGraphics = cpuMap.get("integratedGraphics");
                    if (integratedGraphics != null && integratedGraphics.equals("Отсутствует")) {
                        cpuMap.put("integratedGraphics", null);
                    }
                })
                .map(cpuMap -> objectMapper.convertValue(cpuMap, CPU.class))
                .toList();
        cpus.forEach(System.out::println);
        return cpus;
    }
}
