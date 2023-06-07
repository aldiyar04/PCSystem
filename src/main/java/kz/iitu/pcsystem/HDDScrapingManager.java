package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.HDD;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.HDDRepository;
import kz.iitu.pcsystem.scraper.shopkz.HDDShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class HDDScrapingManager {
    private final HDDRepository hddRepository;
    private final HDDShopKzScraper hddShopKzScraper;

    public void scrape() {
        List<ComponentProduct<HDD>> hddProductsShopKz = hddShopKzScraper.scrape();

        for (ComponentProduct<HDD> hddComponentProduct : hddProductsShopKz) {
            Product product = hddComponentProduct.getProduct();
            HDD hdd = hddComponentProduct.getComponent();
            hdd.addProduct(product);

            Optional<HDD> hddOptional = hddRepository.findById(hdd.getId());
            if (hddOptional.isPresent()) {
                continue;
            }

            byte[] image = FileDownloader.download(hdd.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(hdd, image);

            hddRepository.save(hdd);
        }

//        List<ComponentProduct<HDD>> hddProductsOfSecondaryStores = new ArrayList<>();
//        hddProductsOfSecondaryStores.addAll(hddProductsTechnodom);
//        hddProductsOfSecondaryStores.addAll(hddProductsDnsShop);
//        hddProductsOfSecondaryStores.addAll(hddProductsTechplaza);
//        saveHDDProductsOfSecondaryStores(hddProductsOfSecondaryStores);

        List<HDD> hdds = hddRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (HDD hdd : hdds) {
            System.out.println(hdd);
            for (Product product : hdd.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }

    private void saveHDDProductsOfSecondaryStores(List<ComponentProduct<HDD>> hddProducts) {
        for (ComponentProduct<HDD> hddProduct : hddProducts) {
            String componentId = hddProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<HDD> hddOptional = hddRepository.findById(componentId);
            if (hddOptional.isPresent()) {
                HDD hdd = hddOptional.get();
                Product product = hddProduct.getProduct();
                hdd.addProduct(product);
                hddRepository.save(hdd);
            }
        }
    }
}
