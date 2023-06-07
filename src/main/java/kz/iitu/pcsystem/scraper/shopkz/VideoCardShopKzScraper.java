package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.VideoCard;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class VideoCardShopKzScraper extends ShopKzScraper<VideoCard>{
    private static final Map<String, String> videoCardCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("gpuManufacturer", "Производитель GPU");
        put("gpuSeries", "Серия графического процессора");
        put("chipsetModel", "Модель чипсета");
        put("technologies", "Поддержка технологий");
        put("videoCPUFrequency", "Частота видеопроцессора OC Mode");
        put("videoMemoryFrequency", "Частота видеопамяти, МГц");
        put("videoMemoryType", "Тип видеопамяти");
        put("videoMemoryCapacity", "Объем видеопамяти");
        put("videoMemoryBusWidth", "Разрядность шины видеопамяти");
        put("memoryThroughput", "Пропускная способность памяти, Гбайт/с");
        put("technologies", "Технологии");
        put("connectorInterface", "Интерфейс подключения"); // comp with motherboard
        put("powerSupplySlots", "Разъемы питания"); // comp with powersupply
        put("minPowerSupplyWattage", "Минимальная мощность блока питания, не менее");
        put("length", "Длина видеокарты"); // map
    }};

    @Override
    public List<ComponentProduct<VideoCard>> scrape() {
        return scrapeComponentItems("videokarty", videoCardCharacteristicMap, VideoCard.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        Map<String, String> result = new HashMap<>();



        return result;
    }
}
