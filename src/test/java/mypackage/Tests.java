package mypackage;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Tests extends Helper {
    @Test
    public void testClick() {
        driver.get(baseUrl);
        fieldClick("name");
        fieldClick("birthday");
        fieldClick("city");
        fieldClick("email");
        fieldClick("phone");
        fieldClick("socialLink0");
        driver.findElement(By.xpath("//button")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_default-error-view-visible')]//div[contains(text(), 'Поле обязательное')]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_date ui-form__row_default-error-view-visible')]//div[contains(text(), 'Поле обязательное')]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_dropdownRegion ui-form__row_default-error-view-visible')]//div[contains(text(), 'Поле обязательное')]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_email schema__email_uTUlf ui-form__row_default-error-view-visible')]//div[contains(text(), 'Поле обязательное')]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_tel ui-form__row_default-error-view-visible')]//div[contains(text(), 'Поле обязательное')]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//div[contains(@class, 'ui-form__row ui-form__row_uploadImage schema__uploadImage_33hOq ui-form__row_default-error-view-visible')]//div[contains(text(), 'Поле обязательное')]")).getText());
    }

    @Test
    public void testIncorrect() {
        driver.get(baseUrl);
        textInput("name","абв123");
        textInput("birthday","35.46.9876");
        textInput("email","qwerty77");
        textInput("phone","+7(111) 22");
        textInput("socialLink0","abc");
        driver.findElement(By.xpath("//button")).click();
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис", driver.findElement(By.xpath("//*[contains(text(),'Допустимо использовать только буквы русского алфавита')]")).getText());
        assertEquals("Поле заполнено некорректно", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город проживания'])[1]/following::div[3]")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).getText());
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).getText());
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='загрузите резюме/портфолио'])[1]/following::div[1]")).getText());
    }

    @Test
    public void testChangeTab() {
        driver.get("https://www.google.ru/");
        driver.findElement(By.xpath("//input[contains(@title,'Поиск')]")).sendKeys("мобайл тинькофф");
        driver.findElement(By.xpath("//span[text()='мобайл тинькофф']/b[text()=' тарифы']")).click();
        findUrl();
        waitSeconds();
        switchToWindow();
        assertEquals("Тарифы Тинькофф Мобайла", driver.getTitle());
        switchToMainTab();
        closeCurrentTab();
        switchToWindow();
        assertEquals(baseUrlTariff, driver.getCurrentUrl() );
    }

    @Test
    public void testChangeRegion() {
        driver.get(baseUrlTariff);
        waiting();
        changeCity("Москва");
        checkCity("Москва");
        driver.navigate().refresh();
        checkCity("Москва");
        String priceMoscowDefault = checkPrice();
        selectPack(6);
        checkBox("On","Режим модема");
        checkBox("On","Безлимитные СМС");
        String priceMoscowMax = checkPrice();
        changeCity("Краснодар");
        checkCity("Краснодар");
        String priceKrasnodarDefault = checkPrice();
        selectPack(6);
        checkBox("On","Режим модема");
        checkBox("On","Безлимитные СМС");
        String priceKrasnodarMax = checkPrice();
        assertNotEquals(priceMoscowDefault,priceKrasnodarDefault);
        assertEquals(priceMoscowMax,priceKrasnodarMax);
    }

    @Test
    public void testButton() {
        driver.get(baseUrlTariff);
        waiting();
        selectPack(1);
        waitSeconds();
        checkBox("Off","Мессенджеры");
        checkBox("Off","Социальные сети");
        checkBox("Off","Музыка");
        checkBox("Off","Видео");
        checkBox("Off","Безлимитные СМС");
        waitSeconds();
        assertEquals(checkPrice(),"0 \u20BD");
        driver.findElement(By.xpath("//button")).isEnabled();

    }
}
