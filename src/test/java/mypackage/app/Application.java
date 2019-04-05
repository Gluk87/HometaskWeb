package mypackage.app;

import mypackage.pages.GooglePage;
import mypackage.pages.TinkoffJobPage;
import mypackage.pages.TinkoffTariffPage;
import mypackage.test.BrowsersFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class Application {

    private WebDriver driver;
    public static String baseUrl = "https://www.tinkoff.ru/career/vacancies/";
    public static String baseUrlTariff = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    public static String baseUrlGoogle = "https://www.google.ru/";


    public GooglePage google;
    public TinkoffJobPage tinkoffjob;
    public TinkoffTariffPage tinkofftariff;


    public Application() {
        driver = new EventFiringWebDriver(getDriver());
        ((EventFiringWebDriver) driver).register(new BrowsersFactory.MyListener());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //pages
        google = new GooglePage(driver);
        tinkoffjob = new TinkoffJobPage(driver);
        tinkofftariff = new TinkoffTariffPage(driver);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    private WebDriver getDriver() {
        String browserName = "chrome";
        return BrowsersFactory.buildDriver(browserName);
    }
}
