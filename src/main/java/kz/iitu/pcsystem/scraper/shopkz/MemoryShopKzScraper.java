package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.Memory;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemoryShopKzScraper extends ShopKzScraper<Memory> {
    private static final Map<String, String> memoryCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("memoryType", "Тип памяти");
        put("memorySize", "Объем памяти");
        put("memoryFrequency", "Тактовая частота памяти");
        put("memoryBandwidth", "Пропускная способность памяти");
        put("voltage", "Напряжение питания");
        put("timings", "Тайминги");
        put("features", "Особенности");
        put("height", "Высота");
    }};

    @Override
    public List<ComponentProduct<Memory>> scrape() {
        return scrapeComponentItems("operativnaya-pamyat", memoryCharacteristicMap, Memory.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        return componentItemCharacteristicMap;
    }
}
