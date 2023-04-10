package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.Memory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kz.iitu.pcsystem.util.Util.mapBooleanField;

@Component
@AllArgsConstructor
public class MemoryScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> memoryCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("productUID", "UID товара");
        put("packaging", "Упаковка");
        put("series", "Серия");
        put("formFactor", "Форм-фактор");
        put("amount", "Объём, ГБ");
        put("configuration", "Конфигурация памяти");
        put("clockFrequency", "Тактовая частота, МГц");
        put("throughput", "Тактовая частота, МГц");
        put("ports", "Пропускная способность, Мбайт/сек");
        put("moduleCount", "Количество модулей в комплекте");
        put("memoryType", "Тип памяти");

        put("casLatency", "CAS Latency (CL)");
        put("memoryTimingScheme", "Схема таймингов памяти");
        put("rasToCasDelay", "RAS to CAS Delay (tRCD)");
        put("rowPrechargeDelay", "Row Precharge Delay (tRP)");
        put("isXmpSupported", "Поддержка XMP");
        put("voltage", "Напряжение питания, В");
        put("isRgbBacklight", "Подсветка RGB");
        put("isCoolingRadiator", "Радиатор охлаждения");
        put("backlightSync", "Синхронизация подсветки");
    }};

    public List<Memory> scrapeMemories() {
        List<Memory> memories = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/operativnaja-pamjat", memoryCharacteristicMap)
                .stream()
                .peek(memoryMap -> {
                })
                .map(memoryMap -> objectMapper.convertValue(memoryMap, Memory.class))
                .toList();
        memories.forEach(System.out::println);
        return memories;
    }
}