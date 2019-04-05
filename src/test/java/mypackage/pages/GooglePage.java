package mypackage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GooglePage extends Page {
    public GooglePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[contains(@title,'Поиск')]")
    public WebElement searchField;

    public void inputSearchField(String field) {
        searchField.sendKeys(field);
        logger.info("Введен текст: "+field);
    }

    public void addHint(String field, String hint) {
        driver.findElement(By.xpath("//span[text()='"+field+"']/b[text()=' "+hint+"']")).click();
        logger.info("Выбрана подсказка: "+field+" "+hint);
    }

    public void findUrl(String url){
        By listItems = By.xpath("//cite");
        List<WebElement> items = driver.findElements(listItems);
        for (WebElement element : items){
            if (element.getText().contains(url)){
                element.click();
                break;
            }
        }
        logger.info("Поиск URL: "+url);
    }
}