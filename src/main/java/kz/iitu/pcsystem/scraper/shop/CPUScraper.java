//package kz.iitu.pcsystem.scraper.shop;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kz.iitu.pcsystem.entity.CPU;
//import kz.iitu.pcsystem.scraper.technodom.TechnoDomScraper;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//@AllArgsConstructor
//public class CPUScraper {
//    private final ShopScraper shopScraper;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    private static final Map<String, String> cpuCharacteristicMap = new HashMap<>() {{
//        put("manufacturer", "Производитель");
//        put("cpuType", "Тип процессора");
//        put("model", "Модель");
//        put("socket", "Сокет");
//        put("coreCount", "Общее количество ядер");
//        put("threadCount", "Количество потоков");
//        put("coreClock", "Тактовая частота, ГГц");
//        put("boostCoreClock", "Максимальная тактовая частота, ГГц");
//        put("cacheL1", "Объем кэша L1"); // TODO: match format with Technodom
//        put("cacheL2", "Объем кэша L2"); // TODO: match format with Technodom
//        put("cacheL3", "Объем кэша L3"); // TODO: match format with Technodom
//        put("supportedMemoryTypes", "Поддерживаемый тип оперативной памяти");
//        put("integratedGraphics", "Интегрированная графическая система");
//        put("packaging", "Тип упаковки");
//
//
//
//
//        put("tdp", "Тепловыделение, Вт");
//
//        put("maxMemory", "Максимальный объем ОЗУ");
//        put("maxMemoryChannels", "Макс. число каналов памяти");
//
//        put("pciExpressVersion", "Стандарт PCI Express");
//        put("maxPciExpressChannels", "Стандарт PCI Express");
//        put("criticalTemperature", "Критическая температура, °С");
//        put("supportedOSes", "Поддержка ОС");
//        put("lithography", "Техпроцесс, Нм");
//    }};
//
//    public List<CPU> scrapeCPUs() {
//        List<CPU> cpus = shopScraper
//                .getComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/processory", cpuCharacteristicMap)
//                .stream()
//                .peek(cpuMap -> {
//                    cpuMap.put("model", cpuMap.get("cpuType") + " " + cpuMap.get("model"));
//                    cpuMap.remove("cpuType");
//
////                    String maxMemory = cpuMap.get("maxMemory");
////                    if (maxMemory != null) {
////                        cpuMap.put("maxMemory", maxMemory.split(" ")[0]); // example of maxMemory: 128 Гб
////                    }
////
////                    String integratedGraphics = cpuMap.get("integratedGraphics");
////                    if (integratedGraphics != null && integratedGraphics.equals("Отсутствует")) {
////                        cpuMap.put("integratedGraphics", null);
////                    }
//                })
//                .map(cpuMap -> objectMapper.convertValue(cpuMap, CPU.class))
//                .toList();
//        cpus.forEach(System.out::println);
//        return cpus;
//    }
//}
