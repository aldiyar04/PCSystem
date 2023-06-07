package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.Case;
import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.CaseRepository;
import kz.iitu.pcsystem.scraper.shopkz.CaseShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CaseScrapingManager {
    private final CaseRepository caseRepository;
    private final CaseShopKzScraper caseShopKzScraper;

    public void scrape() {
        List<ComponentProduct<Case>> caseProductsShopKz = caseShopKzScraper.scrape();

        for (ComponentProduct<Case> caseComponentProduct : caseProductsShopKz) {
            Product product = caseComponentProduct.getProduct();
            Case computerCase = caseComponentProduct.getComponent();
            computerCase.addProduct(product);

            Optional<Case> caseOptional = caseRepository.findById(computerCase.getId());
            if (caseOptional.isPresent()) {
                continue;
            }

                byte[] image = FileDownloader.download(computerCase.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(computerCase, image);

            caseRepository.save(computerCase);
        }

//        List<ComponentProduct<Case>> caseProductsOfSecondaryStores = new ArrayList<>();
//        caseProductsOfSecondaryStores.addAll(caseProductsTechnodom);
//        caseProductsOfSecondaryStores.addAll(caseProductsDnsShop);
//        caseProductsOfSecondaryStores.addAll(caseProductsTechplaza);
//        saveCaseProductsOfSecondaryStores(caseProductsOfSecondaryStores);

        List<Case> cases = caseRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 20)).getContent();
        for (Case computerCase : cases) {
            System.out.println(computerCase);
            for (Product product : computerCase.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }

    private void saveCaseProductsOfSecondaryStores(List<ComponentProduct<Case>> caseProducts) {
        for (ComponentProduct<Case> caseProduct : caseProducts) {
            String componentId = caseProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<Case> caseOptional = caseRepository.findById(componentId);
            if (caseOptional.isPresent()) {
                Case computerCase = caseOptional.get();
                Product product = caseProduct.getProduct();
                computerCase.addProduct(product);
                caseRepository.save(computerCase);
            }
        }
    }
}
