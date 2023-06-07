package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.PowerSupply;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PowerSupplyShopKzScraper extends ShopKzScraper<PowerSupply> {
    private static final Map<String, String> powerSupplyCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("formFactor", "Форм-фактор");
        put("power", "Мощность, Вт");
        put("pfc", "Коррекция коэффициента мощности (PFC)");
        put("standardCompliance", "Соответствие стандарту");
        put("efficiency", "КПД");
        put("motherboardConnectors", "Коннекторы питания мат. платы");
        put("graphicsCardConnectors", "Поддержка схемы подключения видеокарты");
        put("pciE6PinCount", "Количество PCI-E коннекторов (6-pin)");
        put("pciE2PinCount", "Количество PCI-E коннекторов (2-pin)");
        put("molex4PinCount", "Количество Molex коннекторов (4-pin)");
        put("sataCount", "Количество SATA коннекторов");
        put("inputVoltage", "Входное напряжение");
        put("inputFrequency", "Входная частота");
        put("outputCurrent3V3", "Выходной ток по линии +3.3В, А");
        put("outputCurrent5V", "Выходной ток по линии +5В, А");
        put("outputCurrent12V", "Выходной ток по линии +12В, А");
        put("fanSize", "Размер вентилятора");
        put("includedItems", "В комплекте");
        put("protectionSystems", "Системы защиты");
        put("length", "Длина блока питания, мм");
        put("dimensions", "Размеры (Ш х В х Г)");
    }};

    @Override
    public List<ComponentProduct<PowerSupply>> scrape() {
        return scrapeComponentItems("bloki-pitaniya", powerSupplyCharacteristicMap, PowerSupply.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        return componentItemCharacteristicMap;
    }
}
