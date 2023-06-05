package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.*;
import kz.iitu.pcsystem.repository.CPURepository;
import kz.iitu.pcsystem.scraper.dnsshop.CPUDnsShopScraper;
import kz.iitu.pcsystem.scraper.shopkz.CPUShopKzScraper;
import kz.iitu.pcsystem.scraper.technodom.*;
import kz.iitu.pcsystem.scraper.techplaza.CPUTechnplazaScraper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

        List<CPU> cpusTechnodom = cpuTechndomScraper.scrape();
        List<CPU> cpusTechplaza = cpuTechplazaScraper.scrape();
        List<CPU> cpusShopKz = cpuShopKzScraper.scrape();
        List<CPU> cpusDnsShop = cpuDnsShopScraper.scrape();

        Map<String, CPU> cpuMapShopKz = convertToMap(cpusShopKz);
        Map<String, CPU> cpuMapTechnodom = convertToMap(cpusTechnodom);
        Map<String, CPU> cpuMapDnsShop = convertToMap(cpusDnsShop);
        Map<String, CPU> cpuMapTechplaza = convertToMap(cpusTechplaza);

//        cpuRepository.saveAll(cpusShopKz);


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

    private Map<String, CPU> convertToMap(List<CPU> cpus) {
        Map<String, CPU> cpuMap = new HashMap<>();
        for (CPU cpu : cpus) {
            cpuMap.put(cpu.getId(), cpu);
        }
        return cpuMap;
    }
}
