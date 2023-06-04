package kz.iitu.pcsystem.util;

import lombok.AllArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@AllArgsConstructor
public class WebDriverUtil {
    private final WebDriver driver;

    public void scrollToPageBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToPageTop() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
    }

    public String getCurrentHtml() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.outerHTML");
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadedCondition = driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        driverWait().until(pageLoadedCondition);
    }

    private WebDriverWait driverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }
}
