package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.HDD;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class HDDScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> hddCharacteristicMap = new HashMap<>() {{
        put("height", "Высота, см");
        put("width", "Ширина, см");
        put("length", "Глубина, см");
        put("weight", "Вес, кг");
        put("capacity", "Емкость накопителя");
        put("formFactor", "Форм-фактор");
        put("spindleSpeed", "Скорость вращения шпинделя, об/мин");
        put("series", "Серия");
        put("type", "Тип");
        put("model", "Модель");
        put("interfacee", "Интерфейс");
        put("purpose", "Назначение");
        put("mtbf", "Время наработки на отказ");
        put("bufferMemory", "Буферная память, Мб");
        put("maxOverloadOn", "Максимальные перегрузки при работе");
        put("averageWaitingTime", "Среднее время ожидания, мс");
        put("noiseLevel", "Уровень шума в активном режиме, дБ");
        put("maxOverloadOff", "Максимальные перегрузки в выключенном состоянии");
    }};

    public List<HDD> scrapeHDDs() {
        List<HDD> hdds = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/kejsy", hddCharacteristicMap)
                .stream()
                .peek(hddMap -> {
                })
                .map(hddMap -> objectMapper.convertValue(hddMap, HDD.class))
                .toList();
        hdds.forEach(System.out::println);
        return hdds;
    }
}
