package kz.iitu.pcsystem.scraper.technodom;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.entity.Identifiable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CPUTechnodomScraper extends TechnodomScraper<CPU> {
    private static final Map<String, String> cpuCharacteristicMap = new HashMap<>() {{
        put("productUID", "UID товара");
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("integratedGraphics", "Интегрированная графическая система");
        put("packaging", "Тип упаковки");
        put("technologies", "Технологии");
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

    @Override
    public Map<String, CPU> scrape() {
        return scrapeComponentItems("processory", cpuCharacteristicMap, CPU.class);
    }

//    @Override
//    public List<CPU> scrape() {
//        return scrapeComponentItems("processory", cpuCharacteristicMap, CPU.class);
//    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> cpuCharacteristicMap) {
        Map<String, String> result = new HashMap<>(cpuCharacteristicMap);

        String maxMemory = cpuCharacteristicMap.get("maxMemory");
        if (maxMemory != null) {
            result.put("maxMemory", maxMemory.split(" ")[0]); // example of maxMemory: 128 Гб
        }

        String integratedGraphics = cpuCharacteristicMap.get("integratedGraphics");
        if (integratedGraphics != null && integratedGraphics.equals("Отсутствует")) {
            result.put("integratedGraphics", null);
        }

        return result;
    }
}
