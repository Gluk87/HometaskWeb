package mypackage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class TinkoffJobPage extends Page {
    public TinkoffJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button")
    private
    WebElement button;

    public void fieldClick(String field){
        driver.findElement(By.xpath("//input[@name='"+field+"']")).click();
        logger.info("Выполнен клик по полю: "+field);
    }

    public void clickButton(){
        button.click();
        logger.info("Нажата копка");
    }

    public void inputText(String field, String text) {
        driver.findElement(By.xpath("//input[@name='" + field + "']")).click();
        driver.findElement(By.xpath("//input[@name='" + field + "']")).clear();
        driver.findElement(By.xpath("//input[@name='" + field + "']")).sendKeys(text);
        logger.info("Введен текст: "+text);
    }

    public void assertFields(String errorText, String nameField) {
        assertEquals(errorText, driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_"+ nameField +"')]//div[contains(@class, 'error-message')]")).getText());
        logger.info("Проверка наличия сообщения: "+errorText);
    }
}
