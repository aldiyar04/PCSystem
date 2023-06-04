//package kz.iitu.pcsystem.scraper.technodom;
//
//import kz.iitu.pcsystem.entity.PowerSupply;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class PowerSupplyTechnodomScraper extends TechnodomScraper<PowerSupply> {
//    private static final Map<String, String> powerSupplyCharacteristicMap = new HashMap<>() {{
//        put("height", "Высота, см");
//        put("width", "Ширина, см");
//        put("length", "Глубина, см");
//        put("formFactor", "Форм-фактор");
//        put("power", "Мощность, Вт");
//        put("frequency", "Частота");
//        put("inputVoltage", "Входное напряжение");
//        put("securityFeatures", "Безопасность");
//        put("certificate", "Сертификат");
//        put("fanDiameter", "Диаметр вентилятора, мм");
//        put("pfcPresence", "Наличие PFC");
//        put("isRgbBacklight", "Подсветка RGB");
//        put("efficiencyPercentage", "Производительность (КПД), %");
//        put("coolingSystem", "Система охлаждения");
//        put("powerSupplyManagement", "Управление блоком питания");
//        put("canDisconnectCables", "Возможность отсоединения кабелей");
//        put("cableLength", "Длина кабеля питания, м");
//        put("cpuPowerConnector", "Коннектор питания процессора");
//        put("sataConnectorCount", "Количество SATA коннекторов, шт");
//        put("molexConnectorCount", "Количество Molex коннекторов (4-pin), шт");
//        put("videoCardPowerConnector", "Коннектор питания видеокарты");
//        put("floppyConnectorCount", "Количество Floppy коннекторов (4-pin), шт");
//        put("motherboardPowerConnector", "Коннектор питания материнской платы");
//    }};
//
//    @Override
//    public List<PowerSupply> scrape() {
//        return scrapeComponentItems("bloki-pitanija", powerSupplyCharacteristicMap, PowerSupply.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
//        return componentItemCharacteristicMap;
//    }
//}
