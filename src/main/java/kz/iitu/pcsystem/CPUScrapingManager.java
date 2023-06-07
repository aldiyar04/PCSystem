package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.CPURepository;
import kz.iitu.pcsystem.scraper.dnsshop.CPUDnsShopScraper;
import kz.iitu.pcsystem.scraper.shopkz.CPUShopKzScraper;
import kz.iitu.pcsystem.scraper.technodom.CPUTechnodomScraper;
import kz.iitu.pcsystem.scraper.techplaza.CPUTechnplazaScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Component
@AllArgsConstructor
public class CPUScrapingManager {
    private final CPUShopKzScraper cpuShopKzScraper;
    private final CPUTechnodomScraper cpuTechndomScraper;
    private final CPUDnsShopScraper cpuDnsShopScraper;
    private final CPUTechnplazaScraper cpuTechplazaScraper;
    private final CPURepository cpuRepository;

    public void scrape() {
        List<ComponentProduct<CPU>> cpuProductsTechnodom = cpuTechndomScraper.scrape();
        List<ComponentProduct<CPU>> cpuProductsTechplaza = cpuTechplazaScraper.scrape();
        List<ComponentProduct<CPU>> cpuProductsShopKz = cpuShopKzScraper.scrape();
        List<ComponentProduct<CPU>> cpuProductsDnsShop = cpuDnsShopScraper.scrape();

        for (ComponentProduct<CPU> cpuProduct : cpuProductsShopKz) {
            Product product = cpuProduct.getProduct();
            CPU cpu = cpuProduct.getComponent();
            cpu.addProduct(product);

            Optional<CPU> cpuOptional = cpuRepository.findById(cpu.getIid());
            if (cpuOptional.isPresent()) {
                continue;
            }

            byte[] image = FileDownloader.download(cpu.getImageUri()); // at this point it is shop.kz uri
            Util.serveComponentImage(cpu, image);

            cpuRepository.save(cpu);
        }

        List<ComponentProduct<CPU>> cpuProductsOfSecondaryStores = new ArrayList<>();
        cpuProductsOfSecondaryStores.addAll(cpuProductsTechnodom);
        cpuProductsOfSecondaryStores.addAll(cpuProductsDnsShop);
        cpuProductsOfSecondaryStores.addAll(cpuProductsTechplaza);
        saveCpuProductsOfSecondaryStores(cpuProductsOfSecondaryStores);

        List<CPU> cpus = cpuRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (CPU cpu : cpus) {
            System.out.println(cpu);
            for (Product product : cpu.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }



    private void saveCpuProductsOfSecondaryStores(List<ComponentProduct<CPU>> cpuProducts) {
        for (ComponentProduct<CPU> cpuProduct : cpuProducts) {
            UUID componentId = cpuProduct.getComponent().getIid();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<CPU> cpuOptional = cpuRepository.findById(componentId);
            if (cpuOptional.isPresent()) {
                CPU cpu = cpuOptional.get();
                Product product = cpuProduct.getProduct();
                cpu.addProduct(product);
                cpuRepository.save(cpu);
            }
        }
    }
}
