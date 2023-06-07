package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.ComponentEntity;
import kz.iitu.pcsystem.entity.Motherboard;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.MotherboardRepository;
import kz.iitu.pcsystem.scraper.shopkz.MotherboardShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

            byte[] image = FileDownloader.download(motherboard.getImageUri()); // at this point it is shop.kz uri
            motherboard.setImage(image);

            motherboardRepository.save(motherboard);
        }

        List<ComponentProduct<Motherboard>> motherboardProductsOfSecondaryStores = new ArrayList<>();
//        motherboardProductsOfSecondaryStores.addAll(motherboardProductsTechnodom);
//        motherboardProductsOfSecondaryStores.addAll(motherboardProductsDnsShop);
//        motherboardProductsOfSecondaryStores.addAll(motherboardProductsTechplaza);
        saveCpuProductsOfSecondaryStores(motherboardProductsOfSecondaryStores);

        List<Motherboard> motherboards = motherboardRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (Motherboard motherboard : motherboards) {
            System.out.println(motherboard);
        }

        List<Motherboard> motherboardsAll = motherboardRepository.findAllWithImage();
        for (ComponentEntity component : motherboardsAll) {
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

        String motherboardImagePath = "src/main/resources/static/images/" + component.getId().replaceAll("\\s", "_") +
                "." + FilenameUtils.getExtension(component.getImageUri());
        try (FileOutputStream fos = new FileOutputStream(motherboardImagePath)) {
            fos.write(component.getImage());
        } catch (IOException e) {
            throw new RuntimeException("Could not write file to " + motherboardImagePath);
        }

        component.setImageUri("/static/images/" + component.getId()); // update shop.kz image uri to ours
    }

    private void saveCpuProductsOfSecondaryStores(List<ComponentProduct<Motherboard>> motherboardProducts) {
        for (ComponentProduct<Motherboard> motherboardProduct : motherboardProducts) {
            String componentId = motherboardProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<Motherboard> motherboardOptional = motherboardRepository.findById(componentId);
            if (motherboardOptional.isPresent()) {
                Motherboard motherboard = motherboardOptional.get();
                Product product = motherboardProduct.getProduct();
                motherboard.addProduct(product);
                motherboardRepository.save(motherboard);
            }
        }
    }
}
