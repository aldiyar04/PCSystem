package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.pojo.ComponentProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotherboardShopKzScraper extends ShopKzScraper<Motherboard> {
    private static final Map<String, String> motherboardCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
        put("socket", "Сокет");
        put("supportedCPUMicroArchitectures", "Поддержка микроархитектуры процессора");
        put("chipset", "Чипсет");
        put("technologies", "Поддержка технологий");
        put("formFactor", "Форм-фактор");
        put("memorySlotCount", "Количество слотов памяти"); // map
        put("supportedMemoryTypes", "Поддерживаемые частоты памяти, МГц"); // map
        put("maxMemory", "Maксимальный объем оперативной памяти");
        put("sata3SlotCount", "Количество разъемов SATA 3 ");
        put("ssdSlotCount", "Разъемы для SSD"); // map
        put("supportedSsdFormFactor", "Разъемы для SSD"); // map
        put("m2Slots", "Интерфейс М2 слота (-ов) ");
        put("pcieSlots", "Количество слотов PCI Express");
        put("pcieVersion", "Стандарт PCI Express");
        put("audioCodec", "Аудиокодек");
        put("internalConnectors", "Внутренние коннекторы");
        put("powerSupplyConnectors", "Коннекторы питания");
        put("length", "Размер (Ш х В)"); // map
        put("width", "Размер (Ш х В)"); // map
    }};

    @Override
    public List<ComponentProduct<Motherboard>> scrape() {
        return scrapeComponentItems("materinskie-platy", motherboardCharacteristicMap, Motherboard.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        Map<String, String> result = new HashMap<>(motherboardCharacteristicMap);

//        String memorySlotCount = result.get("memorySloutCount");
//        if (memorySlotCount != null) {
//            result.put("memorySlotCount", )
//        }

        return result;
    }
}
