package kz.iitu.pcsystem.scraper.techplaza;

import kz.iitu.pcsystem.entity.Identifiable;
import kz.iitu.pcsystem.scraper.AbstractScraper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class TechplazaScraper<T extends Identifiable> extends AbstractScraper<T> {
    private static final String COMPONENTS_BASE_URI = "https://www.dns-shop.kz/catalog/";

    public TechplazaScraper() {
        super("p");
    }

    @Override
    protected Map<String, T> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        return super.scrapeComponentItems(COMPONENTS_BASE_URI + componentUriPart, characteristicMap, componentPojoClass);
    }

    @Override
    protected int getPageCount(Document doc) {
        Elements pageNumbers = doc.select("li[data-role=pagination-page] > " +
                "a:not(.pagination-widget__page-link_next):not(.pagination-widget__page-link_last)"); // these 2 classes are for ">" and ">>" buttons in pagination
        if (pageNumbers.isEmpty()) {
            return 1;
        }
        return Integer.parseInt(pageNumbers.last().text());
    }

    @Override
    protected Elements getItemUriElementsFromPage(Document doc) {
        return doc.getElementsByClass("catalog-product__image-link");
    }

    @Override
    protected Document getPageFromRelativeUri(String relativeUri) {
        return getPage("https://www.dns-shop.kz" + relativeUri + "characteristics");
    }

    @Override
    protected String getCharacteristic(Document doc, String characteristicName) {
        Element characteristicNameElem = doc.select("div.product-characteristics__spec-title:containsOwn(" + characteristicName + ")").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.nextElementSibling();
        return characteristicValueElem.text().strip(); // TODO; account for cases when text is spread out among child elements and tag itself
    }
}
