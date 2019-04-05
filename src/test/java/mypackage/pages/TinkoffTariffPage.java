package mypackage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TinkoffTariffPage extends Page {
    public TinkoffTariffPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class, 'MvnoRegionConfirmation__title')]")
    private
    WebElement region;

    public void checkTitle(String title) {
        assertEquals(title, driver.getTitle());
        logger.info("Произведена проверка заголовка: "+title);
    }

    public void checkUrl(String url) {
        assertEquals(url, driver.getCurrentUrl());
        logger.info("Произведена проверка url: "+url);
    }

    public void waiting(String text){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.xpath("//*[contains(text(),'"+text+"')]")));
        logger.info("Ожидаем появления элемента: "+text);
    }

    public void changeCity(String city){
        if (region.getText().substring(0,10).equals("Ваш регион")) {
            if (driver.findElement(By.xpath("//span[contains(@class, 'MvnoRegionConfirmation__regionName')]")).getText().substring(0,6).equals(city)) {
                driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation')]/span[contains(text(),'Да')]")).click();
            }
            else {
                driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation')]/span[contains(text(),'Нет')]")).click();
                driver.findElement(By.xpath("//div[contains(text(),'"+city+"')]")).click();
            }
        }
        else {
            region.click();
            driver.findElement(By.xpath("//div[contains(text(),'"+city+"')]")).click();
        }
        logger.info("Изменен регион: "+city);
    }

    public void checkCity(String city){
        assertEquals(city, region.getText().substring(0,city.length()));
        logger.info("Произведена проверка города: "+city);
    }

    public String checkPrice(){
        String price = driver.findElement(By.xpath("//h3")).getText().substring(12);
        logger.info("Цена: "+price);
        return price;
    }

    public void selectPack(int x){
        By selectItems = By.xpath("//div[contains(@class, 'ui-select__additional')]");
        List<WebElement> selectAdd = driver.findElements(selectItems);
        for (int j=0; j<selectAdd.size()-1; j++){
            selectAdd.get(j).click();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//div[contains(@class, 'ui-dropdown-field-list ui-dropdown-field-list__opened')]/div[contains(@class, 'ui-dropdown-field-list__item')]["+x+"]/div[contains(@class, 'ui-dropdown-field-list__item-event-handler')]/div[contains(@class, 'ui-dropdown-field-list__item-view ui-select__option_with-subtext_right-side')]")).click();
        }
        logger.info("Выбран пакет: "+x);
    }

    public void setCheckBox(String check, String option){
        boolean box = getCheckBox(option);
        if (box && check.equals("Off")) {
            driver.findElement(By.xpath(getLocator(option))).click();
        }
        else if (!box && check.equals("On")) {
            driver.findElement(By.xpath(getLocator(option))).click();
        }
        logger.info("Установка чек-бокса: "+check+" - "+option);
    }

    private boolean getCheckBox(String option){
        WebElement checkB = driver.findElement(By.xpath(getLocator(option)+"/.././/input[@type='checkbox']"));
        logger.info("Чек-бокса "+option+" установлен?: "+checkB.isSelected());
        return checkB.isSelected();
    }

    private String getLocator(String option){
        logger.info("Найден чек-бокс "+option);
        return "//div[contains(@class, 'CheckboxWithDescription')]/*[contains(text(), '"+option+"')]";
    }

    public void checkButton(){
        driver.findElement(By.xpath("//button")).isEnabled();
        logger.info("Проверка активности кнопки");
    }

}