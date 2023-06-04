package kz.iitu.pcsystem.scraper.shopkz;

import kz.iitu.pcsystem.entity.BaseEntity;
import kz.iitu.pcsystem.scraper.AbstractScraper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public abstract class ShopKzScraper<T extends BaseEntity> extends AbstractScraper<T> {
    private static final String COMPONENTS_BASE_URI = "https://shop.kz/";

    public ShopKzScraper() {
        super("PAGEN_1");
    }

    @Override
    protected List<T> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        return super.scrapeComponentItems(COMPONENTS_BASE_URI + componentUriPart, characteristicMap, componentPojoClass);
    }

//    @Override
//    protected List<T> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
//        return super.scrapeComponentItems(COMPONENTS_BASE_URI + componentUriPart, characteristicMap, componentPojoClass);
//    }

    @Override
    protected int getPageCount(Document doc) {
        Elements paginationContainer = doc.select("div.bx-pagination-container > ul:first-child");
        if (paginationContainer.isEmpty()) {
            return 1;
        }
//        System.out.println(paginationContainer.get(0));
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
        Element characteristicNameElem = doc.select("span:matchesOwn(^" + characteristicName + "$)").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.parent().nextElementSibling();
        return characteristicValueElem.text();
    }
}
