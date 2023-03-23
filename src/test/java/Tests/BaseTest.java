package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Properties;


public class BaseTest {

    public WebDriver driver;
    public Properties config;
    public String uniqueNameForTests;

    @BeforeMethod
    public void setupTest() throws IOException {
        config = new Properties();
        Path dir = Paths.get("src", "test", "java");
        String configPath = dir.toAbsolutePath() + "\\app.xml";
        config.loadFromXML(new FileInputStream(configPath));

        initBrowser(config.getProperty("browser"));

        uniqueNameForTests = Calendar.getInstance().getTimeInMillis() + "";
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    public void navigateToPage(String url) {
        driver.get(url);
    }

    private void initBrowser(String browserName) {
        switch (browserName) {
            case "chrome":
//        Issues with chromedriver 111 and WebDriverManager
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("start-maximized");
                System.setProperty("webdriver.http.factory", "jdk-http-client");
                driver = new EdgeDriver(edgeOptions);
                break;
        }
    }
}
