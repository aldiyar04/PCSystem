package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.CPUCooler;
import kz.iitu.pcsystem.entity.SSD;
import kz.iitu.pcsystem.scraper.technodom.*;
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
    private final SSDScraper ssdScraper;
    private final CPUCoolerScraper cpuCoolerScraper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        List<CPU> cpus = cpuScraper.scrapeCPUs();
//        List<Motherboard> motherboards = motherboardScraper.scrapeMotherboards();
//        List<VideoCard> videoCards = videoCardScraper.scrapeVideoCards();
//        List<Memory> memories = memoryScraper.scrapeMemories();
//        List<SSD> ssds = ssdScraper.scrapeSSDs();
        List<CPUCooler> cpuCoolers = cpuCoolerScraper.scrapeCPUCoolers();
    }
}
