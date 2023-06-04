package kz.iitu.pcsystem.scraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractScraper<T extends BaseEntity> {
    private final String pageQueryParam;
    private final ObjectMapper objectMapper = new ObjectMapper();

//    public abstract Map<String, T> scrape();
    public abstract List<T> scrape();

//    protected Map<String, T> scrapeComponentItems(String componentBasePageUri, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
//        Map<String, T> componentItems = getComponentItemCharacteristicMaps(componentBasePageUri, characteristicMap)
//                .stream()
//                .map(this::mapCharacteristics)
//                .map(componentItemCharacteristicMap -> {
//                    T componentPojo = objectMapper.convertValue(componentItemCharacteristicMap, componentPojoClass);
//                    componentPojo.setId();
//                    return componentPojo;
//                })
//                .collect(Collectors.toMap(componentPojo -> componentPojo.getId(), componentPojo -> componentPojo,
//                        (first, second) -> {
//                            System.out.println("\nDUPLICATES:");
//                            System.out.println(first.getId() + " : " + first);
//                            System.out.println(second.getId() + " : " + second);
//                            System.out.println();
//                            return first;
//                        }));
//        componentItems.forEach((key, value) -> System.out.println(key + " : " + value));
//        return componentItems;
//    }

    protected List<T> scrapeComponentItems(String componentBasePageUri, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        List<T> componentItems = getComponentItemCharacteristicMaps(componentBasePageUri, characteristicMap)
                .stream()
                .map(this::mapCharacteristics)
                .map(componentItemCharacteristicMap -> {
                    T componentPojo = objectMapper.convertValue(componentItemCharacteristicMap, componentPojoClass);
                    componentPojo.setId();
                    return componentPojo;
                })
                .toList();
        componentItems.forEach(System.out::println);
        return componentItems;
    }

    protected abstract Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap);

    public List<Map<String, String>> getComponentItemCharacteristicMaps(String componentBasePageUri, Map<String, String> characteristicMap) {
        return getComponentRelativeUris(componentBasePageUri)
                .stream()
                .map(uri -> getComponentCharacteristicMap(getPageFromRelativeUri(uri), characteristicMap))
                .toList();
    }

    private List<String> getComponentRelativeUris(String basePageUri)  {
        List<String> result = new ArrayList<>();
        Document doc = getPage(basePageUri);
        int pageCount = getPageCount(doc);
        System.out.println("Page count: " + pageCount);

        List<String> urisFromPage = getItemUrisFromPage(doc);
        System.out.println("Page #1: " + urisFromPage.size());
        result.addAll(urisFromPage);
        for (int i = 2; i <= pageCount; i++) {
            doc = getPage(basePageUri + "?" + pageQueryParam + "=" + i);
            urisFromPage = getItemUrisFromPage(doc);
            System.out.println("Page #" + i + ": " + urisFromPage.size());
            result.addAll(urisFromPage);
        }

        return result;
    }

    protected abstract int getPageCount(Document doc);

    private List<String> getItemUrisFromPage(Document doc) {
        return getItemUriElementsFromPage(doc)
                .stream()
                .map(item -> {
//                    System.out.println("URI: " + item.attr("href"));
                    return item.attr("href");
                })
                .toList();
    }

    protected abstract Elements getItemUriElementsFromPage(Document doc);

    private Map<String, String> getComponentCharacteristicMap(Document doc, Map<String, String> characteristicMap) {
        Map<String, String> result = new HashMap<>();

        for (Map.Entry<String, String> entry : characteristicMap.entrySet()) {
            String componentPojoFieldName = entry.getKey();
            String websiteCharacteristicName = entry.getValue();
            result.put(componentPojoFieldName, getCharacteristic(doc, websiteCharacteristicName));
        }

        return result;
    }

    protected abstract Document getPageFromRelativeUri(String relativeUri);

    protected abstract String getCharacteristic(Document doc, String characteristicName);

    protected Document getPage(String uri) {
//        ChromeDriver driver = new ChromeDriver();

        try {
            return Jsoup.connect(uri)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not send request to " + uri);
        }
    }
}