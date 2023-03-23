package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WaitUntil {

    public static boolean domIsLoaded(WebDriver driver) {
        Boolean domLoaded = new WebDriverWait(driver, Duration.ofSeconds(5)).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        return domLoaded;
    }

    public static boolean domHasChanged(WebDriver driver, Duration pollingInterval, Duration timeout) {
        String initialDOM = driver.getPageSource();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(pollingInterval);

        return wait.until(e -> !driver.getPageSource().equals(initialDOM));
    }

}
