//package kz.iitu.pcsystem.scraper.technodom;
//
//import kz.iitu.pcsystem.entity.Motherboard;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static kz.iitu.pcsystem.util.Util.mapBooleanField;
//
//@Component
//public class MotherboardTechnodomScraper extends TechnodomScraper<Motherboard> {
//    private static final Map<String, String> motherboardCharacteristicMap = new HashMap<>() {{
//        // main
//        put("productUID", "UID товара");
//        put("manufacturer", "Производитель");
//        put("model", "Модель");
//        put("supportedOSes", "Поддержка ОС");
//        put("formFactor", "Формат платы");
//        put("supportedCPUs", "Поддерживаемые процессоры");
//        put("chipset", "Чипсет мат. платы");
//        put("features", "Функции и особенности");
//        put("socket", "Сокет");
//        put("supportedCPUGenerations", "Поддержка поколения процессоров");
//        put("bios", "BIOS");
//        put("internalUsbPorts", "Внутренние порты USB на плате");
//
//
//        // memory
//        put("maxMemory", "Максимальный объем оперативной памяти, ГБ");
//        put("isECCSupported", "Поддержка ECC");
//        put("supportedMemoryTypes", "Поддерживаемый тип памяти, МГц");
//        put("memoryConnectorCount", "Количество разъемов и тип памяти");
//
//        // storage
//        put("raid", "Интегрированный RAID-контроллер");
//        put("isNVMeBootSupported", "Поддержка NVMe Boot");
//        put("m2ConnectorCount", "Количество разъемов M.2");
//        put("sataConnectorCount", "Количество разъемов SATA");
//        put("isIntelOptaneMemory", "Intel Optane Memory");
//
//        // communication
//        put("bluetooth", "Bluetooth");
//        put("network", "Сеть"); // TODO: what does this field mean (e.g., value "10/100/1000")?
//        put("supportedWiFiFeatures", "Поддержка Wi-Fi");
//        put("networkController", "Сетевой контроллер");
//
//        // video
//        put("multiGpuSupport", "Поддержка Multi-GPU");
//
//        // audio
//        put("sound", "Звук");
//        put("soundChannelCount", "Количество каналов");
//        put("soundAdapterChipset", "Чипсет звукового адаптера");
//
//        // interfaces
//        put("keyboard", "Клавиатура");
//        put("connectors", "Разъёмы");
//        put("mouse", "Мышь");
//        put("pciExpressSlots", "Количество разъемов PCI Express");
//        put("pciExpressVersions", "Стандарт PCI Express");
//        put("usbPortCount", "Общее количество USB портов");
//        put("backPanelAudioConnectors", "Аудио разъемы на задней панели");
//        put("backPanelVideoConnectors", "Видео разъемы на задней панели");
//        put("powerSupplyConnectorCounts", "Количество разъемов питания процессора");
//    }};
//
//    @Override
//    public List<Motherboard> scrape() {
//        return scrapeComponentItems("materinskie-platy", motherboardCharacteristicMap, Motherboard.class);
//    }
//
//    @Override
//    protected Map<String, String> mapCharacteristics(Map<String, String> motherboardCharacteristicMap) {
//        Map<String, String> result = new HashMap<>(motherboardCharacteristicMap);
//
//        mapBooleanField(result, "isECCSupported");
//        mapBooleanField(result, "isNVMeBootSupported");
//        mapBooleanField(result, "isIntelOptaneMemory");
//
//        return result;
//    }
//}
