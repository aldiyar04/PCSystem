package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.Memory;
import kz.iitu.pcsystem.entity.VideoCard;
import kz.iitu.pcsystem.scraper.technodom.CPUScraper;
import kz.iitu.pcsystem.scraper.technodom.MemoryScraper;
import kz.iitu.pcsystem.scraper.technodom.MotherboardScraper;
import kz.iitu.pcsystem.scraper.technodom.VideoCardScraper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private final CPUScraper cpuScraper;
    private final MotherboardScraper motherboardScraper;
    private final VideoCardScraper videoCardScraper;
    private final MemoryScraper memoryScraper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        List<CPU> cpus = cpuScraper.scrapeCPUs();
//        List<Motherboard> motherboards = motherboardScraper.scrapeMotherboards();
//        List<VideoCard> videoCards = videoCardScraper.scrapeVideoCards();
        List<Memory> memories = memoryScraper.scrapeMemories();
    }
}
