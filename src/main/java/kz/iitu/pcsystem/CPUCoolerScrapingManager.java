package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.CPUCooler;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.CPUCoolerRepository;
import kz.iitu.pcsystem.scraper.shopkz.CPUCoolerShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CPUCoolerScrapingManager {
    private final CPUCoolerRepository cpuCoolerRepository;
    private final CPUCoolerShopKzScraper cpuCoolerShopKzScraper;

    public void scrape() {
        List<ComponentProduct<CPUCooler>> cpuCoolerProductsShopKz = cpuCoolerShopKzScraper.scrape();

        for (ComponentProduct<CPUCooler> cpuCoolerComponentProduct : cpuCoolerProductsShopKz) {
            Product product = cpuCoolerComponentProduct.getProduct();
            CPUCooler cpuCooler = cpuCoolerComponentProduct.getComponent();
            cpuCooler.addProduct(product);

            Optional<CPUCooler> cpuCoolerOptional = cpuCoolerRepository.findById(cpuCooler.getId());
            if (cpuCoolerOptional.isPresent()) {
                continue;
            }

            byte[] image = FileDownloader.download(cpuCooler.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(cpuCooler, image);

            cpuCoolerRepository.save(cpuCooler);
        }

//        List<ComponentProduct<CPUCooler>> cpuCoolerProductsOfSecondaryStores = new ArrayList<>();
//        cpuCoolerProductsOfSecondaryStores.addAll(cpuCoolerProductsTechnodom);
//        cpuCoolerProductsOfSecondaryStores.addAll(cpuCoolerProductsDnsShop);
//        cpuCoolerProductsOfSecondaryStores.addAll(cpuCoolerProductsTechplaza);
//        saveCPUCoolerProductsOfSecondaryStores(cpuCoolerProductsOfSecondaryStores);

        List<CPUCooler> cpuCoolers = cpuCoolerRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (CPUCooler cpuCooler : cpuCoolers) {
            System.out.println(cpuCooler);
            for (Product product : cpuCooler.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }

    private void saveCPUCoolerProductsOfSecondaryStores(List<ComponentProduct<CPUCooler>> cpuCoolerProducts) {
        for (ComponentProduct<CPUCooler> cpuCoolerProduct : cpuCoolerProducts) {
            String componentId = cpuCoolerProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<CPUCooler> cpuCoolerOptional = cpuCoolerRepository.findById(componentId);
            if (cpuCoolerOptional.isPresent()) {
                CPUCooler cpuCooler = cpuCoolerOptional.get();
                Product product = cpuCoolerProduct.getProduct();
                cpuCooler.addProduct(product);
                cpuCoolerRepository.save(cpuCooler);
            }
        }
    }
}
