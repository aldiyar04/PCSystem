package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.entity.SSD;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.SSDRepository;
import kz.iitu.pcsystem.scraper.shopkz.SSDShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SSDScrapingManager {
    private final SSDRepository ssdRepository;
    private final SSDShopKzScraper ssdShopKzScraper;

    public void scrape() {
        List<ComponentProduct<SSD>> ssdProductsShopKz = ssdShopKzScraper.scrape();

        for (ComponentProduct<SSD> ssdComponentProduct : ssdProductsShopKz) {
            Product product = ssdComponentProduct.getProduct();
            SSD ssd = ssdComponentProduct.getComponent();
            ssd.addProduct(product);

            Optional<SSD> ssdOptional = ssdRepository.findById(ssd.getId());
            if (ssdOptional.isPresent())
                continue;

            byte[] image = FileDownloader.download(ssd.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(ssd, image);

            ssdRepository.save(ssd);
        }

//        List<ComponentProduct<SSD>> ssdProductsOfSecondaryStores = new ArrayList<>();
//        ssdProductsOfSecondaryStores.addAll(ssdProductsTechnodom);
//        ssdProductsOfSecondaryStores.addAll(ssdProductsDnsShop);
//        ssdProductsOfSecondaryStores.addAll(ssdProductsTechplaza);
//        saveSSDProductsOfSecondaryStores(ssdProductsOfSecondaryStores);

        List<SSD> ssds = ssdRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (SSD ssd : ssds) {
            System.out.println(ssd);
            for (Product product : ssd.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }

    private void saveSSDProductsOfSecondaryStores(List<ComponentProduct<SSD>> ssdProducts) {
        for (ComponentProduct<SSD> ssdProduct : ssdProducts) {
            String componentId = ssdProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<SSD> ssdOptional = ssdRepository.findById(componentId);
            if (ssdOptional.isPresent()) {
                SSD ssd = ssdOptional.get();
                Product product = ssdProduct.getProduct();
                ssd.addProduct(product);
                ssdRepository.save(ssd);
            }
        }
    }
}
