package kz.iitu.pcsystem.scraper.technodom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TechnoDomScraper {
    public static final String COMPONENTS_BASE_URI = "https://www.technodom.kz/catalog/noutbuki-i-komp-jutery/komplektujuschie";

    public List<Map<String, String>> getComponentItems(String componentBasePageUri, Map<String, String> characteristicMap) {
        return getComponentUris(componentBasePageUri)
                .stream()
                .map(uri -> getComponentCharacteristicMap(uri, characteristicMap))
                .toList();
    }

    private List<String> getComponentUris(String basePageUri)  {
        List<String> result = new ArrayList<>();
        Document doc = getPage(basePageUri);
        int pageCount = getPageCount(doc);
        System.out.println("Page count: " + pageCount);

        List<String> urisFromPage = getItemUrisFromPage(doc);
        System.out.println("Page #1: " + urisFromPage.size());
        result.addAll(urisFromPage);
        for (int i = 2; i <= pageCount; i++) {
            doc = getPage(basePageUri + "?page=" + i);
            urisFromPage = getItemUrisFromPage(doc);
            System.out.println("Page #" + i + ": " + urisFromPage.size());
            result.addAll(urisFromPage);
        }

        return result;
    }

    private int getPageCount(Document doc) {
        Elements pageNumbers = doc.getElementsByClass("Paginator__List-Number");
        if (pageNumbers.isEmpty()) {
            return 1;
        }
        return Integer.parseInt(pageNumbers.last().text());
    }

    private List<String> getItemUrisFromPage(Document doc) {
        return doc.getElementsByClass("category-page-list__item-link")
                .stream()
                .map(item -> {
//                    System.out.println("URI: " + item.attr("href"));
                    return item.attr("href");
                })
                .toList();
    }

    private Map<String, String> getComponentCharacteristicMap(String relativeUri, Map<String, String> characteristicMap) {
        Map<String, String> result = new HashMap<>();

        Document doc = getPage("https://www.technodom.kz/" + relativeUri + "/specifications");

        System.out.println("URL: https://www.technodom.kz/" + relativeUri + "/specifications");

        for (Map.Entry<String, String> entry : characteristicMap.entrySet()) {
            String componentPojoFieldName = entry.getKey();
            String technoDomCharacteristicName = entry.getValue();
            result.put(componentPojoFieldName, getCharacteristic(doc, technoDomCharacteristicName));
        }

        return result;
    }

    private String getCharacteristic(Document doc, String characteristicName) {
        Element characteristicNameElem = doc.select("p:contains(" + characteristicName + ")").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.parent().parent()
                .nextElementSibling().nextElementSibling().firstElementChild();
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
