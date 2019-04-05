package mypackage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Page {
    Logger logger = LoggerFactory.getLogger(Page.class);

    WebDriver driver;
    private WebDriverWait wait;

    Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void open(String url){
        driver.navigate().to(url);
        logger.info("Открыта страница: "+url);
    }

    public void switchToWindow(String windowName) {
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals(windowName);
            }
            return check;
        });
        logger.info("Переключение на вкладку: "+windowName);
    }

    public void closeCurrentTab() {
        driver.close();
        logger.info("Закрыта активная вкладка");
    }

    public void switchToMainTab() {
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        logger.info("Переключились на основную вкладку");
    }

    public void waitSeconds(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Ожидали 5 сек.");
    }

    public void refreshPage(){
        driver.navigate().refresh();
        logger.info("Страница обновлена");
    }
}
