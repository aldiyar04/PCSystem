package kz.iitu.pcsystem.scraper.technodom;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.VideoCard;
import kz.iitu.pcsystem.entity.VideoCard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kz.iitu.pcsystem.util.Util.mapBooleanField;

@Component
@AllArgsConstructor
public class VideoCardScraper {
    private final TechnoDomScraper technoDomScraper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Map<String, String> videoCardCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель видеокарты");
        put("gpuManufacturer", "Производитель графического процессора");
        put("productUID", "UID товара");
        put("technologies", "Технологии");
        put("gpuSeries", "Серия графического процессора");
        put("throughput", "Пропускная способность памяти, Гб/сек");
        put("ports", "Порты");
        put("maxMonitorCount", "Макс. кол-во подключаемых мониторов");
        put("rgb", "Подсветка");
        put("videoMemory", "Видеопамять");
        put("videoMemoryType", "Тип видеопамяти");
        put("gpuModel", "Графический процессор");
        put("lithography", "Техпроцесс, Нм");
        put("coreClock", "Базовая частота GPU, МГц");
        put("boostCoreClock", "Макс. частота GPU в режиме GPU Boost, МГц");
        put("shadingUnitCount", "Количество шейдерных процессоров");
        put("occupiedSlotCount", "Количество занимаемых слотов расширения");
        put("length", "Длина, мм");
        put("recommendedWattage", "Рекомендуемый блок питания компьютера");
        put("powerConnector", "Разъем питания");
        put("wattageConsumption", "Потребление энергии, Вт");
        put("nvidia3VisionSupport", "Поддержка NVIDIA 3D Vision");
        put("isPhysXSupported", "Поддержка PhysX");
        put("maxDisplayResolution", "Максимальное разрешение экрана");
        put("generalPurposeGPUComputingSupport", "Поддержка вычислений общего назначения на GPU");
    }};

    public List<VideoCard> scrapeVideoCards() {
        List<VideoCard> videoCards = technoDomScraper
                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/videokarty", videoCardCharacteristicMap)
                .stream()
                .peek(videoCardMap -> {
                    mapBooleanField(videoCardMap, "isPhysXSupported");

                    String recommendedWattage = videoCardMap.get("recommendedWattage");
                    if (recommendedWattage != null) {
                        videoCardMap.put("recommendedWattage", recommendedWattage.split(" ")[0]); // example: 750 Вт
                    }
                })
                .map(videoCardMap -> objectMapper.convertValue(videoCardMap, VideoCard.class))
                .toList();
        videoCards.forEach(System.out::println);
        return videoCards;
    }
}
