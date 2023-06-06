package kz.iitu.pcsystem.scraper;

import kz.iitu.pcsystem.entity.Component;
import kz.iitu.pcsystem.pojo.ComponentProduct;

import java.util.List;
import java.util.Map;

public abstract class SecondaryStoreScraper<T extends Component> extends AbstractScraper<T> {
    public SecondaryStoreScraper(String pageQueryParam) {
        super(pageQueryParam);
    }

    @Override
    protected List<ComponentProduct<T>> scrapeComponentItems(String componentBasePageUri, Map<String, String> characteristicMap, Class<T> componentPojoClass) {
        List<ComponentProduct<T>> componentProducts = super.scrapeComponentItems(componentBasePageUri, characteristicMap, componentPojoClass)
                .stream().map(componentProduct -> {
                    componentProduct.setComponent(null); // only ID fields are scraped from non-main stores, so almost all fields of component are null, it is to save memory
                    return componentProduct;
                })
                .toList();
        componentProducts.forEach(System.out::println);
        return componentProducts;
    }
}
