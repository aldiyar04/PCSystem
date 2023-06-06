package kz.iitu.pcsystem.scraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.Component;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.WebDriverUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractScraper<T extends Component> {
    private final String pageQueryParam;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private WebDriver driver;
    @Autowired
    private WebDriverUtil driverUtil;

    public AbstractScraper(String pageQueryParam) {
        this.pageQueryParam = pageQueryParam;
    }

    public abstract List<ComponentProduct<T>> scrape();

    protected List<ComponentProduct<T>> scrapeComponentItems(String componentBasePageUri, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        // Product characteristics:
        characteristicMap.put("price", "цена");
        characteristicMap.put("isAvailable", "в наличии");
        characteristicMap.put("uri", "ссылка");
        if (!(this instanceof SecondaryStoreScraper<T>)) characteristicMap.put("imageUri", "ссылка на изображение");

        List<ComponentProduct<T>> componentProducts = getComponentItemCharacteristicMaps(componentBasePageUri, characteristicMap)
                .stream()
                .map(this::mapCharacteristics)
                .map(componentItemCharacteristicMap -> {
                    T component = objectMapper.convertValue(componentItemCharacteristicMap, componentPojoClass);
                    component.setId();

                    Product product = objectMapper.convertValue(componentItemCharacteristicMap, Product.class);
                    product.setComponentId(component.getId());

                    return new ComponentProduct<T>(component, product);
                })
                .toList();
        if (!(this instanceof SecondaryStoreScraper<T>)) componentProducts.forEach(System.out::println);
        return componentProducts;
    }

    protected abstract Map<String, String> mapCharacteristics(Map<String, String> componentItemCharacteristicMap);

    public List<Map<String, String>> getComponentItemCharacteristicMaps(String componentBasePageUri, Map<String, String> characteristicMap) {
        return getComponentRelativeUris(componentBasePageUri)
                .stream()
                .map(uri -> getComponentCharacteristicMap(getPageFromRelativeUri(uri), characteristicMap, uri))
                .toList();
    }

    protected List<String> getComponentRelativeUris(String basePageUri)  {
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

    private Map<String, String> getComponentCharacteristicMap(Document doc, Map<String, String> characteristicMap, String uri) {
        Map<String, String> result = new HashMap<>();

        result.remove("uri");

        for (Map.Entry<String, String> entry : characteristicMap.entrySet()) {
            String componentPojoFieldName = entry.getKey();
            String websiteCharacteristicName = entry.getValue();
            result.put(componentPojoFieldName, getCharacteristic(doc, websiteCharacteristicName));
        }

        result.put("uri", getCurrentUri(uri));

        return result;
    }

    protected abstract Document getPageFromRelativeUri(String relativeUri);

    protected abstract String getCharacteristic(Document doc, String characteristicName);

    protected abstract String getWebsiteBaseUri();

    private String getCurrentUri(String uri) {
        if (uri.startsWith(getWebsiteBaseUri())) return uri;
        return getWebsiteBaseUri() + uri;
    }

    protected Document getPage(String uri) {
        System.out.println("URI: " + uri);
        driver.get(uri);
        driverUtil.waitForPageLoad();
        driverUtil.scrollToPageBottom();
        driverUtil.scrollToPageTop();
        String html = driverUtil.getCurrentHtml();
        return Jsoup.parse(html);
    }
//    protected Document getPage(String uri) {
//        try {
//            return Jsoup.connect(uri)
//                    .get();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Could not send request to " + uri);
//        }
//    }
}
