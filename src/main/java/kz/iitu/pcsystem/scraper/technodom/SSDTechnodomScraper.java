//package kz.iitu.pcsystem.scraper.technodom;
//
//import kz.iitu.pcsystem.entity.SSD;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class SSDTechnodomScraper extends TechnodomScraper<SSD> {
//    private static final Map<String, String> ssdCharacteristicMap = new HashMap<>() {{
//        put("manufacturer", "Производитель");
//        put("model", "Модель");
//        put("formFactor", "Форм-фактор");
//        put("series", "Серия");
//        put("chipType", "Тип чипов");
//        put("capacity", "Емкость накопителя, ГБ");
//        put("writeSpeed", "Скорость записи до, Мбит/сек");
//        put("readSpeed", "Скорость чтения до, Мбит/сек");
//        put("interfaceThroughput", "Пропускная способность интерфейса, Гб/сек");
//        put("pcieVersion", "Версия PCIe");
//        put("height", "Высота, мм");
//        put("width", "Ширина, мм");
//        put("length", "Глубина, мм");
//        put("noiseLevel", "Уровень шума");
//        put("workingTemperature", "Рабочая температура");
//        put("meanTimeBetweenFailures", "Время наработки на отказ");
//        put("idleEnergyConsumption", "Потребление энергии в режиме Idle, Вт");
//        put("activeEnergyConsumption", "Потребление энергии в режиме Active, Вт");
//    }};
//
//    @Override
//    public List<SSD> scrape() {
//        return scrapeComponentItems("ssd-diski", ssdCharacteristicMap, SSD.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> ssdCharacteristicMap) {
//        return ssdCharacteristicMap;
//    }
//}
