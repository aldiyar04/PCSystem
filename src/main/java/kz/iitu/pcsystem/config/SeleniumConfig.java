package kz.iitu.pcsystem.config;

import kz.iitu.pcsystem.StartupRunner;
import kz.iitu.pcsystem.util.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SeleniumConfig {
    static {
        String webDriverPath = StartupRunner.class.getClassLoader().getResource("chromedriver_114.0.5735.90.exe").getPath();
        System.setProperty("webdriver.chrome.driver", webDriverPath);
    }

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    public WebDriver webDriver() {
        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    @Bean
    @Scope("singleton")
    public WebDriverUtil webDriverUtil(WebDriver driver) {
        return new WebDriverUtil(driver);
    }
}
