package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.filtering.CustomSpecificationsBuilder;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private final CPUScrapingManager cpuScrapingManager;
    private final MotherboardScrapingManager motherboardScrapingManager;
    private final CPUCoolerScrapingManager cpuCoolerScrapingManager;
    private final MemoryScrapingManager memoryScrapingManager;
    private final SSDScrapingManager ssdScrapingManager;
    private final HDDScrapingManager hddScrapingManager;
    private final PowerSupplyScrapingManager powerSupplyScrapingManager;
    private final CaseScrapingManager caseScrapingManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        motherboardScrapingManager.scrape();
    }
}
