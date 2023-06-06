package kz.iitu.pcsystem.scraper.shopkz;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.iitu.pcsystem.entity.Component;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.scraper.AbstractScraper;
import kz.iitu.pcsystem.util.WebDriverUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Component
public abstract class ShopKzScraper<T extends Component> extends AbstractScraper<T> {
    public static final String BASE_URI = "https://shop.kz";
    private static final String COMPONENTS_BASE_URI = "https://shop.kz/";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private WebDriver driver;
    @Autowired
    private WebDriverUtil driverUtil;

    public ShopKzScraper() {
        super("PAGEN_1");
    }

    @Override
    protected String getWebsiteBaseUri() {
        return BASE_URI;
    }

    @Override
    protected List<ComponentProduct<T>> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        return super.scrapeComponentItems(COMPONENTS_BASE_URI + componentUriPart, characteristicMap, componentPojoClass);
    }

    @Override
    protected int getPageCount(Document doc) {
        Elements paginationContainer = doc.select("div.bx-pagination-container > ul:first-child");
        if (paginationContainer.isEmpty()) {
            return 1;
        }
        Elements pageNumbers = paginationContainer.get(0)
                .children();
        pageNumbers.remove(pageNumbers.size() - 1); // remove last one that points to next page; the one before it points to last page
        return Integer.parseInt(
                pageNumbers.last()
                        .firstElementChild().firstElementChild()
                        .text()
        );
    }

    @Override
    protected Elements getItemUriElementsFromPage(Document doc) {
        return doc.select("div.bx_catalog_item_title > a");
    }

    @Override
    protected Document getPageFromRelativeUri(String relativeUri) {
        return getPage("https://shop.kz" + relativeUri);
    }

    @Override
    protected String getCharacteristic(Document doc, String characteristicName) {
        if ("ссылка на изображение".equals(characteristicName)) {
            Element imageElement = doc.select(".bx_bigimages_imgcontainer > span > img").first();
            return imageElement.attr("src").substring(2); // starts with "//", then absolute uri
        }

        if ("цена".equals(characteristicName)) {
            String price = doc.select(".item_current_price:containsOwn(₸)").first().text();
            price = price.replaceAll("\\s", "");
            return price.substring(0, price.indexOf("₸"));
        } else if ("в наличии".equals(characteristicName)) {
            return "true";
        }

        Element characteristicNameElem = doc.select("span:matchesOwn(^" + characteristicName + "$)").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.parent().nextElementSibling();
        return characteristicValueElem.text();
    }

    @Override
    protected Document getPage(String uri) {
        driver.get(uri);
        driverUtil.waitForPageLoad();
        String html = driverUtil.getCurrentHtml();
        return Jsoup.parse(html);
    }
}
