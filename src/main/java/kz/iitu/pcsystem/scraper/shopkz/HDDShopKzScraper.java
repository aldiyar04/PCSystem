package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.HDD;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HDDShopKzScraper extends ShopKzScraper<HDD> {
    private static final Map<String, String> hddCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("deviceType", "Тип устройства");
        put("model", "Модель");
        put("line", "Линейка");
        put("formFactor", "Форм-фактор");
        put("interfaceType", "Интерфейс");
        put("interfaceSpeedGbps", "Скорость передачи интерфейса, Гбит/с");
        put("capacityGB", "Емкость диска, ГБ");
        put("bufferMB", "Буфер, МБ");
        put("diskCount", "Число дисков");
        put("headCount", "Число головок");
        put("activePowerConsumptionWatt", "Потребление энергии в режиме Active");
        put("idlePowerConsumptionWatt", "Потребление энергии в режиме простоя");
        put("mtbf", "Наработка на отказ");
        put("dimensions", "Размеры (Ш х В х Г)");
    }};

    @Override
    public List<ComponentProduct<HDD>> scrape() {
        return scrapeComponentItems("zhestkie-diski", hddCharacteristicMap, HDD.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        return componentItemCharacteristicMap;
    }
}
