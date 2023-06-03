package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.*;
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
    private final PowerSupplyScraper powerSupplyScraper;
    private final CaseScraper caseScraper;
    private final HDDScraper hddScraper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<CPU> cpus = cpuScraper.scrape();
        List<Motherboard> motherboards = motherboardScraper.scrape();
        List<VideoCard> videoCards = videoCardScraper.scrape();
        List<Memory> memories = memoryScraper.scrape();
        List<SSD> ssds = ssdScraper.scrape();
        List<CPUCooler> cpuCoolers = cpuCoolerScraper.scrape();
        List<PowerSupply> powerSupplies = powerSupplyScraper.scrape();
        List<Case> cases = caseScraper.scrape();
        List<HDD> hdds = hddScraper.scrape();
    }
}
