package mypackage;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class Tests extends BaseRunner {
    @Test
    public void test1() {
        driver.get("https://www.tinkoff.ru/career/vacancies/");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("birthday")).click();
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("socialLink0")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='условиями передачи информации'])[1]/following::button[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия и имя'])[1]/following::div[2]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия и имя'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город проживания'])[1]/following::div[3]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город проживания'])[1]/following::div[3]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='загрузите резюме/портфолио'])[1]/following::div[1]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='загрузите резюме/портфолио'])[1]/following::div[1]")).getText());
    }

    @Test
    public void test2() {
        driver.get("https://www.tinkoff.ru/career/vacancies/");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("абв123");
        driver.findElement(By.name("birthday")).click();
        driver.findElement(By.name("birthday")).clear();
        driver.findElement(By.name("birthday")).sendKeys("35.46.9876");
        driver.findElement(By.name("city")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("qwerty77");
        driver.findElement(By.name("phone")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("+7(111) 22");
        driver.findElement(By.name("socialLink0")).click();
        driver.findElement(By.name("socialLink0")).clear();
        driver.findElement(By.name("socialLink0")).sendKeys("abc");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Заполните анкету'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='условиями передачи информации'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия и имя'])[1]/following::div[2]")).click();
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия и имя'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).click();
        assertEquals("Поле заполнено некорректно", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Дата рождения'])[1]/following::div[3]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город проживания'])[1]/following::div[3]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город проживания'])[1]/following::div[3]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).click();
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).click();
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Мобильный телефон'])[1]/following::div[2]")).getText());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='загрузите резюме/портфолио'])[1]/following::div[1]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='загрузите резюме/портфолио'])[1]/following::div[1]")).getText());
    }
}
