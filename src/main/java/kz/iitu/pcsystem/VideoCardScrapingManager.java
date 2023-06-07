package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.Product;
import kz.iitu.pcsystem.entity.VideoCard;
import kz.iitu.pcsystem.pojo.ComponentProduct;
import kz.iitu.pcsystem.repository.VideoCardRepository;
import kz.iitu.pcsystem.scraper.shopkz.VideoCardShopKzScraper;
import kz.iitu.pcsystem.util.FileDownloader;
import kz.iitu.pcsystem.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VideoCardScrapingManager {
    private final VideoCardRepository videoCardRepository;
    private final VideoCardShopKzScraper videoCardShopKzScraper;

    public void scrape() {
        List<ComponentProduct<VideoCard>> videoCardProductsShopKz = videoCardShopKzScraper.scrape();

        for (ComponentProduct<VideoCard> videoCardComponentProduct : videoCardProductsShopKz) {
            Product product = videoCardComponentProduct.getProduct();
            VideoCard videoCard = videoCardComponentProduct.getComponent();
            videoCard.addProduct(product);

            byte[] image = FileDownloader.download(videoCard.getImageUri()); // at this point it is shop.kz uri

            Util.serveComponentImage(videoCard, image);

            videoCardRepository.save(videoCard);
        }

//        List<ComponentProduct<VideoCard>> videoCardProductsOfSecondaryStores = new ArrayList<>();
//        videoCardProductsOfSecondaryStores.addAll(videoCardProductsTechnodom);
//        videoCardProductsOfSecondaryStores.addAll(videoCardProductsDnsShop);
//        videoCardProductsOfSecondaryStores.addAll(videoCardProductsTechplaza);
//        saveVideoCardProductsOfSecondaryStores(videoCardProductsOfSecondaryStores);

        List<VideoCard> videoCards = videoCardRepository.findAllSortedByProductCountDesc(PageRequest.of(0, 10)).getContent();
        for (VideoCard videoCard : videoCards) {
            System.out.println(videoCard);
            for (Product product : videoCard.getProducts()) {
                System.out.println(product);
            }
            System.out.println();
        }
    }

    private void saveVideoCardProductsOfSecondaryStores(List<ComponentProduct<VideoCard>> videoCardProducts) {
        for (ComponentProduct<VideoCard> videoCardProduct : videoCardProducts) {
            String componentId = videoCardProduct.getProduct().getComponentId();
            if (componentId == null) {
                throw new IllegalStateException("componentId may not be null");
            }
            Optional<VideoCard> videoCardOptional = videoCardRepository.findById(componentId);
            if (videoCardOptional.isPresent()) {
                VideoCard videoCard = videoCardOptional.get();
                Product product = videoCardProduct.getProduct();
                videoCard.addProduct(product);
                videoCardRepository.save(videoCard);
            }
        }
    }
}
