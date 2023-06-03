package kz.iitu.pcsystem.scraper.technodom;

import kz.iitu.pcsystem.scraper.AbstractScraper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public abstract class TechnoDomScraper<T> extends AbstractScraper<T> {
    public static final String COMPONENTS_BASE_URI = "https://www.technodom.kz/catalog/noutbuki-i-komp-jutery/komplektujuschie";

    public TechnoDomScraper() {
        super("page");
    }

    @Override
    protected List<T> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        return super.scrapeComponentItems(TechnoDomScraper.COMPONENTS_BASE_URI + "/" + componentUriPart, characteristicMap, componentPojoClass);
    }

    @Override
    protected int getPageCount(Document doc) {
        Elements pageNumbers = doc.getElementsByClass("Paginator__List-Number");
        if (pageNumbers.isEmpty()) {
            return 1;
        }
        return Integer.parseInt(pageNumbers.last().text());
    }

    @Override
    protected Elements getItemUriElementsFromPage(Document doc) {
        return doc.getElementsByClass("category-page-list__item-link");
    }

    @Override
    protected Document getPageFromRelativeUri(String relativeUri) {
        return getPage("https://www.technodom.kz" + relativeUri + "/specifications");
    }

    @Override
    protected String getCharacteristic(Document doc, String characteristicName) {
        Element characteristicNameElem = doc.select("p:matchesOwn(^" + characteristicName + "$)").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.parent().parent()
                .nextElementSibling().nextElementSibling().firstElementChild();
        return characteristicValueElem.text();
    }
}
