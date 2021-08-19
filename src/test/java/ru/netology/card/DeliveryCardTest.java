package ru.netology.card;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryCardTest {
    @Test
    void ShouldAllFieldsDone() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Екатеринбург");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79500095259");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime())));
    }

    @Test
    void doNotChooseCity() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79500095259");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=city] .input__sub").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void doNotChooseDate() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Екатеринбург");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue("");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79500095259");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=date] .input__sub").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Неверно введена дата"));
    }

    @Test
    void cityNotFromList() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Истра");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79500095259");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=city] .input__sub").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Доставка в выбранный город недоступна"));
    }
    @Test
    void nameWithLatinLetters (){
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Екатеринбург");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Ivanov Ivan");
        form.$("[data-test-id=phone] input").setValue("+79500095259");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=name] .input__sub").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void incorrectNumberFormat() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Екатеринбург");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+7950009525");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=phone] .input__sub").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void noAgreementWith() {
        open("http://localhost:9999/");
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Екатеринбург");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79500095259");
        form.$("[role=button] .button__content").click();
        $("[data-test-id=agreement] .checkbox__text").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

}









