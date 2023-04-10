package kz.iitu.pcsystem;

import kz.iitu.pcsystem.entity.CPU;
import kz.iitu.pcsystem.scraper.technodom.CPUScraper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StartupRunner implements ApplicationRunner {
    private final CPUScraper cpuScraper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<CPU> cpus = cpuScraper.scrapeCPUs();
    }
}
