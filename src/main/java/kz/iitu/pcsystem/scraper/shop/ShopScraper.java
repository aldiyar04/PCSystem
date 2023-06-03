package kz.iitu.pcsystem.scraper.shop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShopScraper {

    private Map<String, String> getComponentCharacteristicMap(String relativeUri, Map<String, String> characteristicMap) {
        Map<String, String> result = new HashMap<>();

        Document doc = getPage("https://shop.kz/offer/protsessor-amd-ryzen-5-5500-am4-oem");

        for (Map.Entry<String, String> entry : characteristicMap.entrySet()) {
            String componentPojoFieldName = entry.getKey();
            String shopCharacteristicName = entry.getValue();
            result.put(componentPojoFieldName, getCharacteristic(doc, shopCharacteristicName));
        }

        return result;
    }

    private String getCharacteristic(Document doc, String characteristicName) {
        Element characteristicNameElem = doc.select("span:matchesOwn(^" + characteristicName + "$)").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.parent().nextElementSibling();
        return characteristicValueElem.text();
    }

    private Document getPage(String uri) {
        try {
            return Jsoup.connect(uri)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not send request to " + uri);
        }
    }
}
