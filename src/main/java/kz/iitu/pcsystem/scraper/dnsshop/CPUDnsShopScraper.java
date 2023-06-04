package kz.iitu.pcsystem.scraper.dnsshop;

import kz.iitu.pcsystem.entity.CPU;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CPUDnsShopScraper extends DnsShopScraper<CPU> {
    private static final Map<String, String> cpuCharacteristicMap = new HashMap<>() {{
        put("model", "Модель");
    }};

    @Override
    public Map<String, CPU> scrape() {
        return scrapeComponentItems("17a899cd16404e77/processory", cpuCharacteristicMap, CPU.class);
    }

    @Override
    protected Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap) {
        Map<String, String> result = new HashMap<>(componentItemCharacteristicMap);

        result.put("manufacturer", result.get("model").split(" ")[0]);
        result.put("model", Arrays.stream(result.get("model").split(" ")).skip(1).collect(Collectors.joining(" ")));

        return result;
    }
}
