package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.SSD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class SSDScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> ssdCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("formFactor", "Форм-фактор");
        put("series", "Серия");
        put("chipType", "Тип чипов");
        put("capacity", "Емкость накопителя, ГБ");
        put("writeSpeed", "Скорость записи до, Мбит/сек");
        put("readSpeed", "Скорость чтения до, Мбит/сек");
        put("interfaceThroughput", "Пропускная способность интерфейса, Гб/сек");
        put("pcieVersion", "Версия PCIe");
        put("height", "Высота, мм");
        put("width", "Ширина, мм");
        put("length", "Глубина, мм");
        put("noiseLevel", "Уровень шума");
        put("workingTemperature", "Рабочая температура");
        put("meanTimeBetweenFailures", "Время наработки на отказ");
        put("idleEnergyConsumption", "Потребление энергии в режиме Idle, Вт");
        put("activeEnergyConsumption", "Потребление энергии в режиме Active, Вт");
    }};

    public List<SSD> scrapeSSDs() {
        List<SSD> ssds = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/ssd-diski", ssdCharacteristicMap)
                .stream()
                .peek(ssdMap -> {
                })
                .map(ssdMap -> objectMapper.convertValue(ssdMap, SSD.class))
                .toList();
        ssds.forEach(System.out::println);
        return ssds;
    }
}
