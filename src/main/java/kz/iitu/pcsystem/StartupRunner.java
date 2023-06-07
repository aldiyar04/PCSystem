package kz.iitu.pcsystem;

import kz.iitu.pcsystem.util.CPUScrapingManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private final CPUScrapingManager cpuScrapingManager;
    private final MotherboardScrapingManager motherboardScrapingManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        motherboardScrapingManager.scrape();
    }
}
