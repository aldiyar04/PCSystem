package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.Memory;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.MemoryRepository;
import kz.iitu.pcsystem.scraper.shopkz.MemoryShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class MemoryScrapingManager {
    private final MemoryRepository memoryRepository;
    private final MemoryShopKzScraper memoryShopKzScraper;

    public void scrape() {
        List<ComponentProduct<Memory>> memoryProductsShopKz = memoryShopKzScraper.scrape();

        for (ComponentProduct<Memory> memoryComponentProduct : memoryProductsShopKz) {
            Product product = memoryComponentProduct.getProduct();
            Memory memory = memoryComponentProduct.getComponent();
            memory.addProduct(product);

            Optional<Memory> memoryOptional = memoryRepository.findById(memory.getIid());
            if (memoryOptional.isPresent()) {
                continue;
            }

            byte[] image = FileDownloader.download(memory.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(memory, image);

            memoryRepository.save(memory);
        }

//        List<ComponentProduct<Memory>> memoryProductsOfSecondaryStores = new ArrayList<>();
//        memoryProductsOfSecondaryStores.addAll(memoryProductsTechnodom);
//        memoryProductsOfSecondaryStores.addAll(memoryProductsDnsShop);
//        memoryProductsOfSecondaryStores.addAll(memoryProductsTechplaza);
//        saveMemoryProductsOfSecondaryStores(memoryProductsOfSecondaryStores);

        List<Memory> memories = memoryRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (Memory memory : memories) {
            System.out.println(memory);
            for (Product product : memory.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }
}
