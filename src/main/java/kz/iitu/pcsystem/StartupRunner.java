package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.*;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.CPURepository;
import kz.iitu.pcsystem.repository.ProductRepository;
import kz.iitu.pcsystem.scraper.dnsshop.CPUDnsShopScraper;
import kz.iitu.pcsystem.scraper.shopkz.CPUShopKzScraper;
import kz.iitu.pcsystem.scraper.shopkz.ShopKzScraper;
import kz.iitu.pcsystem.scraper.technodom.*;
import kz.iitu.pcsystem.scraper.techplaza.CPUTechnplazaScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private final CPUTechnodomScraper cpuTechndomScraper;
//    private final MotherboardTechnodomScraper motherboardTechnodomScraper;
//    private final VideoCardTechnodomScraper videoCardTechnodomScraper;
//    private final MemoryTechnodomScraper memoryTechnodomScraper;
//    private final SSDTechnodomScraper ssdTechnodomScraper;
//    private final CPUCoolerTechnodomScraper cpuCoolerTechnodomScraper;
//    private final PowerSupplyTechnodomScraper powerSupplyTechnodomScraper;
//    private final CaseTechnodomScraper caseTechnodomScraper;
//    private final HDDTechndomScraper hddTechndomScraper;
    private final CPUShopKzScraper cpuShopKzScraper;
    private final CPUDnsShopScraper cpuDnsShopScraper;
    private final CPUTechnplazaScraper cpuTechplazaScraper;

    private final CPURepository cpuRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        List<CPU> cpus = cpuTechndomScraper.scrape();
//        List<Motherboard> motherboards = motherboardTechnodomScraper.scrape();
//        List<VideoCard> videoCards = videoCardTechnodomScraper.scrape();
//        List<Memory> memories = memoryTechnodomScraper.scrape(); // not scraped
//        List<SSD> ssds = ssdTechnodomScraper.scrape();
//        List<CPUCooler> cpuCoolers = cpuCoolerTechnodomScraper.scrape();
//        List<PowerSupply> powerSupplies = powerSupplyTechnodomScraper.scrape();
//        List<Case> cases = caseTechnodomScraper.scrape();
//        List<HDD> hdds = hddTechndomScraper.scrape();


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

        List<CPU> cpus = cpuRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 10)).getContent();
        System.out.println("\n\n\nAll CPUs paginated:");
        for (CPU cpu : cpus) {
            System.out.println(cpu);
        }

        System.out.println("finding all cpus");

        List<CPU> cpusAll = cpuRepository.findAllWithImage();
        for (CPU cpu : cpusAll) {
            System.out.println(cpu.getImageUri());
            if (!cpu.getImageUri().contains("shop.kz")) {
                System.out.println("continue");
                // already saved to resource folder and imageUri is ours
                continue;
            }

            System.out.println("serving image");

//            URI imageFolderUri = URI.create(this.getClass().getClassLoader().getResource("/static/images").getPath());
//            String cpuImagePath = imageFolderUri.resolve(cpu.getId()).getPath();
            String cpuImagePath = "src/main/resources/static/images/" + cpu.getId().replaceAll("\\s", "_") +
                    "." + FilenameUtils.getExtension(cpu.getImageUri());
            try (FileOutputStream fos = new FileOutputStream(cpuImagePath)) {
                fos.write(cpu.getImage());
            } catch (IOException e) {
                throw new RuntimeException("Could not write file to " + cpuImagePath);
            }

            cpu.setImageUri("/static/images/" + cpu.getId()); // update shop.kz image uri to ours
        }
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


    private void checkCountAmongStores(List<ComponentProduct<CPU>> cpusShopKz,
                                       List<ComponentProduct<CPU>> cpusTechnodom,
                                       List<ComponentProduct<CPU>> cpusDnsShop,
                                       List<ComponentProduct<CPU>> cpusTechplaza) {
        Map<Integer, Integer> cpuCounts = new HashMap<>();

        Map<String, CPU> cpuMapShopKz = convertToMap(cpusShopKz);
        Map<String, CPU> cpuMapTechnodom = convertToMap(cpusTechnodom);
        Map<String, CPU> cpuMapDnsShop = convertToMap(cpusDnsShop);
        Map<String, CPU> cpuMapTechplaza = convertToMap(cpusTechplaza);

        cpuMapShopKz.forEach((cpuId, cpuShopKz) -> {
            int count = 1;
            if (cpuMapTechnodom.get(cpuId) != null) {
                count++;
            }
            if (cpuMapDnsShop.get(cpuId) != null) {
                count++;
            }
            if (cpuMapTechplaza.get(cpuId) != null) {
                count++;
            }
            cpuCounts.put(count, cpuCounts.getOrDefault(count, 0) + 1);
        });

        System.out.println("\n\n\nCOMMON COUNT (SHOP-KZ)");
        cpuCounts.forEach((storeCount, cpuCount) -> {
            System.out.println(storeCount + " : " + cpuCount);
        });
    }

    private Map<String, CPU> convertToMap(List<ComponentProduct<CPU>> cpuProducts) {
        List<CPU> cpus = cpuProducts.stream().map(ComponentProduct::getComponent).toList();

        Map<String, CPU> cpuMap = new HashMap<>();
        for (CPU cpu : cpus) {
            cpuMap.put(cpu.getId(), cpu);
        }
        return cpuMap;
    }
}
