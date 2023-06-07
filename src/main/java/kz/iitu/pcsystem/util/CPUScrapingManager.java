package kz.iitu.pcsystem.util;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.entity.ComponentEntity;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.CPURepository;
import kz.iitu.pcsystem.scraper.dnsshop.CPUDnsShopScraper;
import kz.iitu.pcsystem.scraper.shopkz.CPUShopKzScraper;
import kz.iitu.pcsystem.scraper.technodom.CPUTechnodomScraper;
import kz.iitu.pcsystem.scraper.techplaza.CPUTechnplazaScraper;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.PageRequest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

            byte[] image = FileDownloader.download(cpu.getImageUri()); // at this point it is shop.kz uri
            cpu.setImage(image);

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
        }

        List<CPU> cpusAll = cpuRepository.findAllWithImage();
        for (ComponentEntity component : cpusAll) {
            serveComponentImage(component);
        }
    }

    public static void serveComponentImage(ComponentEntity component) {
        System.out.println(component.getImageUri());
        if (!component.getImageUri().contains("shop.kz")) {
            System.out.println("continue");
            // already saved to resource folder and imageUri is ours
            return;
        }

        System.out.println("serving image");

        String cpuImagePath = "src/main/resources/static/images/" + component.getId().replaceAll("\\s", "_") +
                "." + FilenameUtils.getExtension(component.getImageUri());
        try (FileOutputStream fos = new FileOutputStream(cpuImagePath)) {
            fos.write(component.getImage());
        } catch (IOException e) {
            throw new RuntimeException("Could not write file to " + cpuImagePath);
        }

        component.setImageUri("/static/images/" + component.getId()); // update shop.kz image uri to ours
    }

    private void saveCpuProductsOfSecondaryStores(List<ComponentProduct<CPU>> cpuProducts) {
        for (ComponentProduct<CPU> cpuProduct : cpuProducts) {
            String componentId = cpuProduct.getProduct().getComponentId();
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
