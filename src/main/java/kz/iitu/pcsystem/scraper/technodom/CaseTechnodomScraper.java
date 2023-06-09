//package kz.iitu.pcsystem.scraper.technodom;
//
//import kz.iitu.pcsystem.entity.Case;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class CaseTechnodomScraper extends TechnodomScraper<Case> {
//    private static final Map<String, String> caseCharacteristicMap = new HashMap<>() {{
//        put("height", "Высота, см");
//        put("width", "Ширина, см");
//        put("length", "Глубина, см");
//        put("weight", "Вес, кг");
//        put("formFactor", "Форм-фактор");
//        put("backlight", "Подсветка");
//        put("type", "Тип");
//        put("material", "Материал");
//        put("usbPorts", "USB");
//        put("expansionSlotCount", "Количество слотов расширения");
//        put("audioJacks", "Аудио разъемы");
//        put("portsLocation", "Расположение портов");
//        put("mounting", "Крепление");
//        put("indicators", "Индикаторы");
//        put("maxVideoCardLength", "Макс. длина видеокарты, мм");
//        put("dustFilter", "Противопылевой фильтр");
//        put("isWindowOnSide", "Окно на боковой стенке");
//        put("compartment25Count", "Количество отсеков 2.5\\\"");
//        put("compartment35Count", "Количество отсеков 3.5\\\"");
//        put("supportedFanTypes", "Поддержка вентиляторов");
//        put("isLiquidCoolingPlace", "Место для установки СЖО");
//        put("maxCpuCoolerHeight", "Макс. высота процессорного кулера, мм");
//        put("cpuCoolingIncluded", "Охлаждение корпуса в комплекте");
//        put("powerSupplyLocation", "Расположение блока питания");
//    }};
//
//    @Override
//    public List<Case> scrape() {
//        return scrapeComponentItems("kejsy", caseCharacteristicMap, Case.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> caseCharacteristicMap) {
//        return caseCharacteristicMap;
//    }
//}
