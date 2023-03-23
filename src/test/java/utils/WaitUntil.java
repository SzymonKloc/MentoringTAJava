package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class WaitUntil {

    private static WebDriver driver;


    public WaitUntil(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

//    visibility, clickable,

}
