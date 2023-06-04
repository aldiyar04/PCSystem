//package kz.iitu.pcsystem.scraper.technodom;
//
//import kz.iitu.pcsystem.entity.VideoCard;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static kz.iitu.pcsystem.util.Util.mapBooleanField;
//
//@Component
//public class VideoCardTechnodomScraper extends TechnodomScraper<VideoCard> {
//    private static final Map<String, String> videoCardCharacteristicMap = new HashMap<>() {{
//        put("productUID", "UID товара");
//        put("manufacturer", "Производитель видеокарты");
//        put("gpuManufacturer", "Производитель графического процессора");
//        put("technologies", "Технологии");
//        put("gpuSeries", "Серия графического процессора");
//        put("throughput", "Пропускная способность памяти, Гб/сек");
//        put("ports", "Порты");
//        put("maxMonitorCount", "Макс. кол-во подключаемых мониторов");
//        put("rgb", "Подсветка");
//        put("videoMemory", "Видеопамять");
//        put("videoMemoryType", "Тип видеопамяти");
//        put("gpuModel", "Графический процессор");
//        put("lithography", "Техпроцесс, Нм");
//        put("coreClock", "Базовая частота GPU, МГц");
//        put("boostCoreClock", "Макс. частота GPU в режиме GPU Boost, МГц");
//        put("shadingUnitCount", "Количество шейдерных процессоров");
//        put("occupiedSlotCount", "Количество занимаемых слотов расширения");
//        put("length", "Длина, мм");
//        put("recommendedWattage", "Рекомендуемый блок питания компьютера");
//        put("powerConnector", "Разъем питания");
//        put("wattageConsumption", "Потребление энергии, Вт");
//        put("nvidia3VisionSupport", "Поддержка NVIDIA 3D Vision");
//        put("isPhysXSupported", "Поддержка PhysX");
//        put("maxDisplayResolution", "Максимальное разрешение экрана");
//        put("generalPurposeGPUComputingSupport", "Поддержка вычислений общего назначения на GPU");
//    }};
//
//    @Override
//    public List<VideoCard> scrape() {
//        return scrapeComponentItems("videokarty", videoCardCharacteristicMap, VideoCard.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> videoCardCharacteristicMap) {
//        Map<String, String> result = new HashMap<>(videoCardCharacteristicMap);
//
//        mapBooleanField(result, "isPhysXSupported");
//
//        String recommendedWattage = result.get("recommendedWattage");
//        if (recommendedWattage != null) {
//            result.put("recommendedWattage", recommendedWattage.split(" ")[0]); // example: 750 Вт
//        }
//
//        return result;
//    }
//}
