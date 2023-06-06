//package kz.iitu.pcsystem.scraper.techplaza;
//
//import kz.iitu.pcsystem.entity.Motherboard;
//import kz.iitu.pcsystem.pojo.ComponentProduct;
//import org.jsoup.nodes.Document;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MotherboardTechplazaScraper extends TechplazaScraper<Motherboard> {
//    private static final Map<String, String> motherboardCharacteristicMap = new HashMap<>() {{
//        put("manufacturer", "Производитель");
//        put("model", "Модель");
//    }};
//
//    @Override
//    public List<ComponentProduct<Motherboard>> scrape() {
//        return scrapeComponentItems("Materinskieplaty", motherboardCharacteristicMap, Motherboard.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
//        return componentItemCharacteristicMap;
//    }
//
//    @Override
//    protected String getCharacteristic(Document doc, String characteristicName) {
//        String characteristic = super.getCharacteristic(doc, characteristicName);
//        if (characteristic != null) return characteristic;
//
//        if ("Модель".equals(characteristicName)) {
//            String cpuType = doc.select("td:containsOwn(Тип процессора)").first()
//                    .nextElementSibling().text();
//            if (cpuType.startsWith("AMD")) cpuType = cpuType.substring("AMD ".length());
//            String title = doc.select("h1[itemprop=name]").first().text();
//            title = title.substring("Процессор ".length());
//            String manufacturer = title.split(" ")[0];
//
//            String model;
//            if (title.indexOf(cpuType) + cpuType.length() == title.strip().length()) {
//                model = title.substring(title.indexOf(cpuType));
//            } else if ("Intel".equals(manufacturer) && cpuType.contains("Core i")) {
//                model = cpuType + title.substring(title.indexOf(cpuType) + cpuType.length()).split(" ")[0];
//            } else {
//                model = cpuType + " " + title.substring(title.indexOf(cpuType) + cpuType.length()).strip().split(" ")[0];
//            }
//            return model;
//        }
//
//        return null;
//    }
//}
