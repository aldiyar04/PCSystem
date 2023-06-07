package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.MotherboardRepository;
import kz.iitu.pcsystem.scraper.shopkz.MotherboardShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MotherboardScrapingManager {
    private final MotherboardRepository motherboardRepository;
    private final MotherboardShopKzScraper motherboardShopKzScraper;

    public void scrape() {
        List<ComponentProduct<Motherboard>> motherboardProductsShopKz = motherboardShopKzScraper.scrape();

        for (ComponentProduct<Motherboard> motherboardComponentProduct : motherboardProductsShopKz) {
            Product product = motherboardComponentProduct.getProduct();
            Motherboard motherboard = motherboardComponentProduct.getComponent();
            motherboard.addProduct(product);

            Optional<Motherboard> motherboardOptional = motherboardRepository.findByCustomId(motherboard.getId());
            if (motherboardOptional.isPresent()) {
                continue;
            }

            byte[] image = FileDownloader.download(motherboard.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(motherboard, image);

            System.out.println("MOTHERBOARD: " + motherboard.getIid() + " " + motherboard.getId());

            motherboardRepository.save(motherboard);
        }

//        List<ComponentProduct<Motherboard>> motherboardProductsOfSecondaryStores = new ArrayList<>();
//        motherboardProductsOfSecondaryStores.addAll(motherboardProductsTechnodom);
//        motherboardProductsOfSecondaryStores.addAll(motherboardProductsDnsShop);
//        motherboardProductsOfSecondaryStores.addAll(motherboardProductsTechplaza);
//        saveMotherboardProductsOfSecondaryStores(motherboardProductsOfSecondaryStores);

        List<Motherboard> motherboards = motherboardRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        System.out.println("LIST SIZE: " + motherboards.size());
        for (Motherboard motherboard : motherboards) {
            System.out.println(motherboard);
            for (Product product : motherboard.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }
}
