package kz.iitu.pcsystem.scraper.technodom;

import kz.iitu.pcsystem.entity.ComponentEntity;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.scraper.SecondaryStoreScraper;
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
public abstract class TechnodomScraper<T extends ComponentEntity> extends SecondaryStoreScraper<T> {
    private static final String BASE_URI = "https://www.technodom.kz";
    private static final String COMPONENTS_BASE_URI = "https://www.technodom.kz/catalog/noutbuki-i-komp-jutery/komplektujuschie/";
    @Autowired
    private WebDriver driver;
    @Autowired
    private WebDriverUtil driverUtil;

    public TechnodomScraper() {
        super("page");
    }

    @Override
    protected String getWebsiteBaseUri() {
        return BASE_URI;
    }

    @Override
    protected List<ComponentProduct<T>> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        return super.scrapeComponentItems(COMPONENTS_BASE_URI + componentUriPart, characteristicMap, componentPojoClass);
    }


//    @Override
//    protected List<String> getComponentRelativeUris(String basePageUri) {
//        List<String> result = new ArrayList<>();
//        getPage(basePageUri);
//
//        WebElement confirmingCityModal = driver.findElement(By.xpath("//p[contains(text(), 'Ваш город')]"));
//        if (confirmingCityModal != null) {
//            WebElement closeModalButton = confirmingCityModal.findElement(By.xpath("./following-sibling::*/button/*[contains(text(), 'Да')]"));
//            closeModalButton.click();
//        }
//
//        WebElement cityListButton = driver.findElement(By.cssSelector("button > p.city-selector__title"));
//        cityListButton.click();
//
//        WebElement moreCitiesButton = driver.findElement(By.xpath("//button//*[contains(text(), 'еще')]"));
//        moreCitiesButton.click();
//
//        List<String> baseComponentUrisForSpecificCities = new ArrayList<>();
//
//        WebElement parentOfUriElements = driver.findElement(By.xpath("//p[contains(text(), 'Выберите город')]/following-sibling::*"));
//        String html = parentOfUriElements.getAttribute("outerHTML");
//        Document doc = Jsoup.parse(html);
//        Elements links = doc.select("a");
//        for (Element link : links) {
//            String uri = link.attr("href");
//            baseComponentUrisForSpecificCities.add(uri);
//        }
//
//        for (String uri : baseComponentUrisForSpecificCities) {
//            result.addAll(super.getComponentRelativeUris(BASE_URI + uri));
//        }
//
//        return result;
//    }

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
        return getPage(BASE_URI + relativeUri + "/specifications");
    }

    @Override
    protected String getCharacteristic(Document doc, String characteristicName) {
        if ("цена".equals(characteristicName)) {
            String price = doc.select("p:containsOwn(₸)").first().text();
            System.out.println("PRICE: " + price);
            price = price.replaceAll("\\s|\\u00A0", ""); // replace NBSP as well
            return price.substring(0, price.indexOf("₸"));
        } else if ("в наличии".equals(characteristicName)) {
            return "true";
        }

        Element characteristicNameElem = doc.select("p:matchesOwn(^" + characteristicName + "$)").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.parent().parent()
                .nextElementSibling().nextElementSibling().firstElementChild();
        return characteristicValueElem.text();
    }

    @Override
    protected Document getPage(String uri) {
        System.out.println("URI: " + uri);
        driver.get(uri);
        driverUtil.waitForPageLoad();
        driverUtil.scrollToPageBottom();
        String html = driverUtil.getCurrentHtml();
        return Jsoup.parse(html);
    }
}
