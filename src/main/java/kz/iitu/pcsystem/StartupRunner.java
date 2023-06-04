package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.*;
import kz.iitu.pcsystem.repository.CPURepository;
import kz.iitu.pcsystem.scraper.dnsshop.CPUDnsShopScraper;
import kz.iitu.pcsystem.scraper.shopkz.CPUShopKzScraper;
import kz.iitu.pcsystem.scraper.technodom.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final CPURepository cpuRepository;

    static {
        String webDriverPath = StartupRunner.class.getClassLoader().getResource("chromedriver_114.0.5735.90.exe").getPath();
        System.setProperty("webdriver.chrome.driver", webDriverPath);
    }

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
        Map<Integer, Integer> cpuCounts = new HashMap<>();

//        List<CPU> cpusShopKz = cpuShopKzScraper.scrape();
        List<CPU> cpusTechnodom = cpuTechndomScraper.scrape();
//        List<CPU> cpusDnsShop = cpuDnsShopScraper.scrape();

        cpuRepository.saveAll(cpusTechnodom);

        List<String> allCpuIds = new ArrayList<>();


//        cpusTechnodom.forEach((cpuId, cpuTechnodom) -> {
//            int count = 1;
//            if (cpusShopKz.get(cpuId) != null) {
//                count++;
//            }
//            if (cpusDnsShop.get(cpuId) != null) {
//                count++;
//            }
//            cpuCounts.put(count, cpuCounts.getOrDefault(count, 0) + 1);
//        });

//        cpusShopKz.forEach((cpuId, cpuTechnodom) -> {
//            int count = 1;
//            if (cpusTechnodom.get(cpuId) != null) {
//                count++;
//            }
//            if (cpusDnsShop.get(cpuId) != null) {
//                count++;
//            }
//            cpuCounts.put(count, cpuCounts.getOrDefault(count, 0) + 1);
//        });

//        System.out.println("COMMON COUNT");
//        cpuCounts.forEach((storeCount, cpuCount) -> {
//            System.out.println(storeCount + " : " + cpuCount);
//        });
    }
}
