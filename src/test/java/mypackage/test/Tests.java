package mypackage.test;

import mypackage.pages.GooglePage;
import mypackage.pages.TinkoffJobPage;
import mypackage.pages.TinkoffTariffPage;
import org.junit.Test;

import static mypackage.app.Application.*;

public class Tests extends BaseRunner {
    @Test
    public void testClick() {
        TinkoffJobPage tinkoffJob = app.tinkoffjob;
        tinkoffJob.open(baseUrl);
        tinkoffJob.fieldClick("name");
        tinkoffJob.fieldClick("birthday");
        tinkoffJob.fieldClick("city");
        tinkoffJob.fieldClick("email");
        tinkoffJob.fieldClick("phone");
        tinkoffJob.fieldClick("socialLink0");
        tinkoffJob.clickButton();
        tinkoffJob.assertFields("Поле обязательное", "default");
        tinkoffJob.assertFields("Поле обязательное","date");
        tinkoffJob.assertFields("Поле обязательное","dropdownRegion");
        tinkoffJob.assertFields("Поле обязательное","email");
        tinkoffJob.assertFields("Поле обязательное","tel");
        tinkoffJob.assertFields("Поле обязательное","uploadImage");
    }

    @Test
    public void testIncorrect() {
        TinkoffJobPage tinkoffJob = app.tinkoffjob;
        tinkoffJob.open(baseUrl);
        tinkoffJob.inputText("name","абв123");
        tinkoffJob.inputText("birthday","35.46.9876");
        tinkoffJob.inputText("email","qwerty77");
        tinkoffJob.inputText("phone","+7(111) 22");
        tinkoffJob.inputText("socialLink0","abc");
        tinkoffJob.clickButton();
        tinkoffJob.assertFields("Допустимо использовать только буквы русского алфавита и дефис", "default");
        tinkoffJob.assertFields("Поле заполнено некорректно","date");
        tinkoffJob.assertFields("Поле обязательное","dropdownRegion");
        tinkoffJob.assertFields("Введите корректный адрес эл. почты","email");
        tinkoffJob.assertFields("Номер телефона должен состоять из 10 цифр, начиная с кода оператора","tel");
        tinkoffJob.assertFields("Поле обязательное","uploadImage");
    }

    @Test
    public void testChangeTab() {
        GooglePage google = app.google;
        google.open(baseUrlGoogle);
        google.inputSearchField("мобайл тинькофф");
        google.addHint("мобайл тинькофф","тарифы");
        google.findUrl(baseUrlTariff);
        google.waitSeconds();
        google.switchToWindow("Тарифы Тинькофф Мобайла");
        TinkoffTariffPage tinkoffTariff = app.tinkofftariff;
        tinkoffTariff.checkTitle("Тарифы Тинькофф Мобайла");
        tinkoffTariff.switchToMainTab();
        tinkoffTariff.closeCurrentTab();
        tinkoffTariff.switchToWindow("Тарифы Тинькофф Мобайла");
        tinkoffTariff.checkUrl(baseUrlTariff);
    }

    @Test
    public void testChangeRegion() {
        TinkoffTariffPage tinkoffTariff = app.tinkofftariff;
        tinkoffTariff.open(baseUrlTariff);
        tinkoffTariff.waiting("Общая цена");
        tinkoffTariff.changeCity("Москва");
        tinkoffTariff.checkCity("Москва");
        tinkoffTariff.refreshPage();
        tinkoffTariff.checkCity("Москва");
        String priceMoscowDefault = tinkoffTariff.checkPrice();
        tinkoffTariff.selectPack(6);
        tinkoffTariff.setCheckBox("On","Режим модема");
        tinkoffTariff.setCheckBox("On","Безлимитные СМС");
        String priceMoscowMax = tinkoffTariff.checkPrice();
        tinkoffTariff.changeCity("Краснодар");
        tinkoffTariff.checkCity("Краснодар");
        String priceKrasnodarDefault = tinkoffTariff.checkPrice();
        tinkoffTariff.selectPack(6);
        tinkoffTariff.setCheckBox("On","Режим модема");
        tinkoffTariff.setCheckBox("On","Безлимитные СМС");
        String priceKrasnodarMax = tinkoffTariff.checkPrice();
        tinkoffTariff.checkPriceEquals(false,priceMoscowDefault,priceKrasnodarDefault);
        tinkoffTariff.checkPriceEquals(true,priceMoscowMax,priceKrasnodarMax);
    }

    @Test
    public void testButton() {
        TinkoffTariffPage tinkoffTariff = app.tinkofftariff;
        tinkoffTariff.open(baseUrlTariff);
        tinkoffTariff.waiting("Общая цена");
        tinkoffTariff.selectPack(1);
        tinkoffTariff.waitSeconds();
        tinkoffTariff.setCheckBox("Off","Мессенджеры");
        tinkoffTariff.setCheckBox("Off","Социальные сети");
        tinkoffTariff.setCheckBox("Off","Музыка");
        tinkoffTariff.setCheckBox("Off","Видео");
        tinkoffTariff.setCheckBox("Off","Безлимитные СМС");
        tinkoffTariff.checkPriceEquals(true,tinkoffTariff.checkPrice(),"0 \u20BD");
        tinkoffTariff.checkButton();
    }
}
