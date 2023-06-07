package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
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
        put("m2Slots", "Интерфейс М2 слота (-ов) ");
        put("pcieSlots", "Количество слотов PCI Express");
        put("pcieVersion", "Стандарт PCI Express");
        put("audioCodec", "Аудиокодек");
        put("internalConnectors", "Внутренние коннекторы");
        put("powerSupplyConnectors", "Коннекторы питания");
        put("length", "Размер (Ш х В)"); // map
    }};

    @Override
    public List<ComponentProduct<Motherboard>> scrape() {
        return scrapeComponentItems("materinskie-platy", motherboardCharacteristicMap, Motherboard.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        Map<String, String> result = new HashMap<>(componentItemCharacteristicMap);

        String memorySlotCount = result.get("memorySlotCount"); // example: 4 x DDR4
        if (memorySlotCount != null) {
            result.put("memorySlotCount", memorySlotCount.split(" ")[0]);
        }

        String supportedMemoryTypes = result.get("supportedMemoryTypes"); // example (frequencies in MHz): 2133, 2400, 2667, 2933, 3200
        if (supportedMemoryTypes != null) {
            String memoryType = memorySlotCount.split(" ")[2].strip();
            String[] frequencies = supportedMemoryTypes.replace("\\s", "").split(",");

            for (int i = 0; i < frequencies.length; i++) {
                frequencies[i] = memoryType + "-" + frequencies[i].strip();
            }
            result.put("supportedMemoryTypes", String.join(", ", frequencies));
        }

        String maxMemory = result.get("maxMemory"); // example: 64 Гб
        if (maxMemory != null) {
            result.put("maxMemory", maxMemory.split(" ")[0]);
        }

        String ssdSlotCount = result.get("ssdSlotCount"); // example: 2 x M.2
        if (ssdSlotCount != null) {
            result.put("ssdSlotCount", ssdSlotCount.split(" ")[0]);
        }

        String supportedSsdFormFactor = result.get("supportedSsdFormFactor");
        if (supportedSsdFormFactor != null) {
            result.put("supportedSsdFormFactor", ssdSlotCount.split(" ")[2].strip());
        }

        String length = result.get("length");
        if (length != null) {
            String actualLength = length.split(" ")[0];
            String width = length.split(" ")[2];

            result.put("length", actualLength);
            result.put("width", width);
        }

        System.out.println("\nMAP:");
        result.forEach((key, val) -> System.out.println(key + " : " + val));

        return result;
    }
}
