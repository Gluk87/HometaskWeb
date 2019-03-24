package mypackage;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    WebDriver driver;
    private String browserName;

    @Before
    public void setUp() {
       // System.setProperty("browser","opera");
        browserName = System.getProperty("browser");
        driver = getDriver();
        String baseUrl = "https://www.tinkoff.ru/career/vacancies/";
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
