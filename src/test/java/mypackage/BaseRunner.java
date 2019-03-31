package mypackage;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    WebDriver driver;
    private String browserName;
    String baseUrl = "https://www.tinkoff.ru/career/vacancies/";
    String baseUrlTariff = "https://www.tinkoff.ru/mobile-operator/tariffs/";

    @Before
    public void setUp() {
        System.setProperty("browser","chrome");
        browserName = System.getProperty("browser");
        driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private WebDriver getDriver() {
        return BrowserFactory.buildDriver(browserName);
    }
}
