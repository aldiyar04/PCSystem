//package kz.iitu.pcsystem.scraper.technodom;
//
//import kz.iitu.pcsystem.entity.CPUCooler;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class CPUCoolerTechnodomScraper extends TechnodomScraper<CPUCooler> {
//    private static final Map<String, String> cpuCoolerCharacteristicMap = new HashMap<>() {{
//        put("noiseLevel", "Уровень шума, дБ");
//        put("backlight", "Подсветка");
//        put("mountType", "Тип крепления");
//        put("supportedSockets", "Поддерживаемые сокеты");
//        put("thermalPaste", "Термопаста");
//        put("fanCount", "Количество вентиляторов в комплекте, шт");
//        put("height", "Высота, мм");
//        put("width", "Ширина, мм");
//        put("length", "Глубина, мм");
//        put("weight", "Вес, гр");
//        put("voltage", "Напряжение питания, В");
//        put("connector", "Коннектор");
//        put("tdp", "Рассеиваемая мощность TDP, Вт");
//        put("coolerHeight", "Высота кулера, мм");
//        put("radiatorMaterial", "Материал радиатора");
//        put("baseMaterial", "Материал основания");
//        put("coolerDesign", "Конструкция кулера");
//        put("isRotationSpeedAdjustable", "Регулировка скорости вращения");
//        put("fanBearingType", "Тип подшипника вентилятора");
//        put("cfm", "Воздушный поток, CFM");
//        put("rotationalSpeed", "Скорость вращения");
//        put("fanDimensions", "Размеры вентилятора");
//        put("coolingSystemDesign", "Конструкция системы охлаждения");
//    }};
//
//    @Override
//    public List<CPUCooler> scrape() {
//        return scrapeComponentItems("kulery-dlja-processorov", cpuCoolerCharacteristicMap, CPUCooler.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> cpuCoolerCharacteristicMap) {
//        return cpuCoolerCharacteristicMap;
//    }
//}
