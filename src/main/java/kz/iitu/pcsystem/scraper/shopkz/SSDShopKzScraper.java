package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.SSD;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SSDShopKzScraper extends ShopKzScraper<SSD> {
    private static final Map<String, String> ssdCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("formFactor", "Форм-фактор");
        put("interfaceType", "Интерфейс");
        put("capacityGB", "Емкость диска, ГБ");
        put("tbw", "Суммарный объем данных TBW, ТБ");
        put("readSpeedMBps", "Скорость чтения, МБ/с, до");
        put("writeSpeedMBps", "Скорость записи, МБ/с, до");
        put("mtbf", "Наработка на отказ");
        put("dimensions", "Размеры (Ш х В х Г)");
    }};

    @Override
    public List<ComponentProduct<SSD>> scrape() {
        return scrapeComponentItems("ssd-diski", ssdCharacteristicMap, SSD.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        return componentItemCharacteristicMap;
    }
}
