package kz.iitu.pcsystem.scraper.techplaza;

import kz.iitu.pcsystem.entity.BaseEntity;
import kz.iitu.pcsystem.scraper.AbstractScraper;
import kz.iitu.pcsystem.util.WebDriverUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public abstract class TechplazaScraper<T extends BaseEntity> extends AbstractScraper<T> {
    private static final String COMPONENTS_BASE_URI = "https://techplaza.kz/Komplektujushhie/";
    @Autowired
    private WebDriver driver;
    @Autowired
    private WebDriverUtil driverUtil;

    public TechplazaScraper() {
        super("page");
    }

    @Override
    protected List<T> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        return super.scrapeComponentItems(COMPONENTS_BASE_URI + componentUriPart, characteristicMap, componentPojoClass);
    }

    @Override
    protected int getPageCount(Document doc) {
        Elements pageNumbers = doc.select("ul.pagination > li > a");
        if (pageNumbers.isEmpty()) {
            return 1;
        }
        pageNumbers = new Elements(pageNumbers.subList(0, pageNumbers.size() - 2)); // last 2 links are ">" and ">|" buttons
        return Integer.parseInt(pageNumbers.last().text());
    }

    @Override
    protected Elements getItemUriElementsFromPage(Document doc) {
        return doc.select(".product-name > a");
    }

    @Override
    protected Document getPageFromRelativeUri(String relativeUri) {
        String actuallyAbsoluteUri = relativeUri;
        Document doc = getPage(actuallyAbsoluteUri);
        WebElement openCharacteristicsButton = driver.findElement(By.cssSelector("a[href=\"#tab-specification\"]"));
        openCharacteristicsButton.click();
        return doc;
    }

    @Override
    protected String getCharacteristic(Document doc, String characteristicName) {
        if ("Производитель".equals(characteristicName)) {
            return doc.select("b:containsOwn(Производитель)").first()
                    .nextElementSibling()
                    .firstElementChild().text();
        }
        return null;
    }

    @Override
    protected Document getPage(String uri) {
        driver.get(uri);
        driverUtil.waitForPageLoad();
        String html = driverUtil.getCurrentHtml();
        return Jsoup.parse(html);
    }
}
