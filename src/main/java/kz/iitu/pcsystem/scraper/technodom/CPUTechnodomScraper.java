package kz.iitu.pcsystem.scraper.technodom;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CPUTechnodomScraper extends TechnodomScraper<CPU> {
    private static final Map<String, String> cpuCharacteristicMap = new HashMap<>() {{
        put("manufacturer", "Производитель");
        put("model", "Модель");
    }};

    @Override
    public List<ComponentProduct<CPU>> scrape() {
        return scrapeComponentItems("processory", cpuCharacteristicMap, CPU.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> cpuCharacteristicMap) {
        return cpuCharacteristicMap;
    }
}
