package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

class Helper extends BaseRunner {

    void changeCity(String city){
        if (driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__title_DOqnW')]")).getText().substring(0,10).equals("Ваш регион")) {
            if (driver.findElement(By.xpath("//span[contains(@class, 'MvnoRegionConfirmation__regionName_3IZfl')]")).getText().substring(0,6).equals(city)) {
                driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation')]/span[contains(text(),'Да')]")).click();
            }
            else {
                driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation')]/span[contains(text(),'Нет')]")).click();
                driver.findElement(By.xpath("//div[contains(text(),'"+city+"')]")).click();
            }
        }
        else {
            driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__title_DOqnW')]")).click();
            driver.findElement(By.xpath("//div[contains(text(),'"+city+"')]")).click();
        }
    }

    void checkCity(String city){
        assertEquals(city, driver.findElement(By.xpath("//div[contains(@class, 'MvnoRegionConfirmation__title_DOqnW')]")).getText().substring(0,city.length()));
    }

    String checkPrice(){
        return driver.findElement(By.xpath("//h3")).getText().substring(12);
    }

    void selectPack(int x){
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
    }



    void checkBox(String check, String option){
        String locator = "//div[contains(@class, 'CheckboxWithDescription')]/*[contains(text(), '"+option+"')]";
        WebElement checkB = driver.findElement(By.xpath(locator+"/.././/input[@type='checkbox']"));
        if (checkB.isSelected() && check.equals("Off")) {
            driver.findElement(By.xpath(locator)).click();
        }
        else if (!checkB.isSelected() && check.equals("On")) {
            driver.findElement(By.xpath(locator)).click();
        }
    }


    void findUrl(){
        By listItems = By.xpath("//cite");
        List<WebElement> items = driver.findElements(listItems);
        for (WebElement element : items){
            if (element.getText().contains(baseUrlTariff)){
                element.click();
                break;
            }
        }
    }

    void switchToWindow() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> {
            boolean check = false;
            for (String title : driver.getWindowHandles()) {
                driver.switchTo().window(title);
                check = d.getTitle().equals("Тарифы Тинькофф Мобайла");
            }
            return check;
        });
    }

    void closeCurrentTab() {
        driver.close();
    }

    void switchToMainTab() {
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

    void waitSeconds(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void waiting(){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.xpath("//*[contains(text(),'Общая цена')]")));
    }

    void textInput(String field, String text){
        driver.findElement(By.xpath("//input[@name='"+field+"']")).click();
        driver.findElement(By.xpath("//input[@name='"+field+"']")).clear();
        driver.findElement(By.xpath("//input[@name='"+field+"']")).sendKeys(text);
    }

    void fieldClick(String field){
        driver.findElement(By.xpath("//input[@name='"+field+"']")).click();
    }


}
