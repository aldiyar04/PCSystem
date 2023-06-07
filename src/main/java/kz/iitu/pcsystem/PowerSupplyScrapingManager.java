package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.PowerSupply;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.PowerSupplyRepository;
import kz.iitu.pcsystem.scraper.shopkz.PowerSupplyShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PowerSupplyScrapingManager {
    private final PowerSupplyRepository powerSupplyRepository;
    private final PowerSupplyShopKzScraper powerSupplyShopKzScraper;

    public void scrape() {
        List<ComponentProduct<PowerSupply>> powerSupplyProductsShopKz = powerSupplyShopKzScraper.scrape();

        for (ComponentProduct<PowerSupply> powerSupplyComponentProduct : powerSupplyProductsShopKz) {
            Product product = powerSupplyComponentProduct.getProduct();
            PowerSupply powerSupply = powerSupplyComponentProduct.getComponent();
            powerSupply.addProduct(product);

            Optional<PowerSupply> powerSupplyOptional = powerSupplyRepository.findById(powerSupply.getId());
            if (powerSupplyOptional.isPresent()) {
                continue;
            }

            byte[] image = FileDownloader.download(powerSupply.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(powerSupply, image);

            powerSupplyRepository.save(powerSupply);
        }

//        List<ComponentProduct<PowerSupply>> powerSupplyProductsOfSecondaryStores = new ArrayList<>();
//        powerSupplyProductsOfSecondaryStores.addAll(powerSupplyProductsTechnodom);
//        powerSupplyProductsOfSecondaryStores.addAll(powerSupplyProductsDnsShop);
//        powerSupplyProductsOfSecondaryStores.addAll(powerSupplyProductsTechplaza);
//        savePowerSupplyProductsOfSecondaryStores(powerSupplyProductsOfSecondaryStores);

        List<PowerSupply> powerSupplies = powerSupplyRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (PowerSupply powerSupply : powerSupplies) {
            System.out.println(powerSupply);
            for (Product product : powerSupply.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }

    private void savePowerSupplyProductsOfSecondaryStores(List<ComponentProduct<PowerSupply>> powerSupplyProducts) {
        for (ComponentProduct<PowerSupply> powerSupplyProduct : powerSupplyProducts) {
            String componentId = powerSupplyProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<PowerSupply> powerSupplyOptional = powerSupplyRepository.findById(componentId);
            if (powerSupplyOptional.isPresent()) {
                PowerSupply powerSupply = powerSupplyOptional.get();
                Product product = powerSupplyProduct.getProduct();
                powerSupply.addProduct(product);
                powerSupplyRepository.save(powerSupply);
            }
        }
    }
}
