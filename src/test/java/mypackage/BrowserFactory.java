package mypackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

class BrowserFactory {
    static WebDriver buildDriver(String browserName) {
        switch (browserName) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--disable-notifications");
                return new FirefoxDriver(firefoxOptions);

            case "opera":
                OperaOptions operaOptions = new OperaOptions();
                operaOptions.addArguments("--disable-notifications");
                return new OperaDriver(operaOptions);

            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                return new ChromeDriver(options);
        }
    }
}
