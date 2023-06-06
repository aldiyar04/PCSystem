package kz.iitu.pcsystem.scraper.dnsshop;

import kz.iitu.pcsystem.entity.Component;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.scraper.SecondaryStoreScraper;
import kz.iitu.pcsystem.util.WebDriverUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Component
public abstract class DnsShopScraper<T extends Component> extends SecondaryStoreScraper<T> {
    private static final String COMPONENTS_BASE_URI = "https://www.dns-shop.kz/catalog/";
    @Autowired
    private WebDriver driver;
    @Autowired
    private WebDriverUtil driverUtil;

    public DnsShopScraper() {
        super("p");
    }

    @Override
    protected String getWebsiteBaseUri() {
        return "https://www.dns-shop.kz";
    }

    @Override
    protected List<ComponentProduct<T>> scrapeComponentItems(String componentUriPart, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
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
        if ("цена".equals(characteristicName)) {
            WebElement element = driverUtil.waitForElementPresence(By.className("product-buy__price"));
            String price = element.getText();
            price = price.replaceAll("\\s|\\u00A0", ""); // replace NBSP as well
            System.out.println("PRICE: " + price);
            return price.substring(0, price.indexOf("₸"));
        } else if ("в наличии".equals(characteristicName)) {
            return "true"; // TODO: scrape from element
//            WebElement element = driverUtil.waitForElementPresence(By.className("order-avail-wrap"));
//
//            String isAvailable;
//
//            String classes = element.getAttribute("class");
//            if (classes != null && classes.contains("order-avail-wrap_not-avail")) {
//                return printAndReturn("false");
//            }
//
//            String html = element.getAttribute("outerHTML");
//            System.out.println("HTML: " + html);
//
////            String availability = element.findElement(By.cssSelector(":nth-child(2)"))
////                    .findElement(By.cssSelector(":nth-child(1)"))
////                    .getText();
//            List<WebElement> children = element.findElements(By.xpath(".//*"));
//            if (children.isEmpty()) {
//                return printAndReturn("false");
//            }
////            WebElement available = driverUtil.waitForElementPresence(element, By.className("available"));
////            if (available == null) {
////                return printAndReturn("false");
////            }
//            WebElement isAvailableElem = element.findElement(By.xpath("./*[2]/*[contains(text(), 'магазин')]")); // example: в 2 магазинах
//            if (isAvailableElem != null) {
//                return printAndReturn("true");
//            }
////            if (availability != null && availability.contains("магазин")) {
////                isAvailable = "true";
////                return "true";
////            }
//            return printAndReturn("false");
//            // CLASS: order-avail-wrap, VALUE: Товара нет в наличии (with usual whitespace on sides)
//
////            String availability = doc.getElementsByClass("available").first()
////                    .nextElementSibling()
////                    .firstElementChild().text();
////            return Boolean.toString(availability.toLowerCase().strip().startsWith("в")); // examples: в 2 магазинах OR 9 июня (пт) OR
        }

        Element characteristicNameElem = doc.select("div.product-characteristics__spec-title:containsOwn(" + characteristicName + ")").first();
        if (characteristicNameElem == null) return null;
        Element characteristicValueElem = characteristicNameElem.nextElementSibling();
        return characteristicValueElem.text().strip(); // TODO; account for cases when text is spread out among child elements and tag itself
    }

    private String printAndReturn(String bool) {
        System.out.println("IS AVAILAVBLE: " + bool);
        return bool;
    }
}
