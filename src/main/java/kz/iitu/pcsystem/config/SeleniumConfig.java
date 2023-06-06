package kz.iitu.pcsystem.config;

import kz.iitu.pcsystem.StartupRunner;
import kz.iitu.pcsystem.util.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

@Configuration
public class SeleniumConfig {
    static {
//        String webDriverPath = StartupRunner.class.getClassLoader().getResource("chromedriver_114.0.5735.90.exe").getPath();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_114.0.5735.90.exe");
    }

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    public WebDriver webDriver() {
        boolean isHeadless = false;
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(isHeadless);
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver =  new ChromeDriver(options);
        if (!isHeadless) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    @Bean
    @Scope("singleton")
    public WebDriverUtil webDriverUtil(WebDriver driver) {
        return new WebDriverUtil(driver);
    }
}
