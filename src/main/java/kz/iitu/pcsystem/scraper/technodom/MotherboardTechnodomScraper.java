package kz.iitu.pcsystem.scraper.technodom;

import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kz.iitu.pcsystem.util.Util.mapBooleanField;

@Component
public class MotherboardTechnodomScraper extends TechnodomScraper<Motherboard> {
    private static final Map<String, String> motherboardCharacteristicMap = new HashMap<>() {{
        // main
        put("manufacturer", "Производитель");
        put("model", "Модель");
    }};

    @Override
    public List<ComponentProduct<Motherboard>> scrape() {
        return scrapeComponentItems("materinskie-platy", motherboardCharacteristicMap, Motherboard.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> motherboardCharacteristicMap) {
        Map<String, String> result = new HashMap<>(motherboardCharacteristicMap);
        return result;
    }
}
