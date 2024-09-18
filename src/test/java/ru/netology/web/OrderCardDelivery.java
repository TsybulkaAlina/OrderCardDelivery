package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardDelivery {

    @Test
    public void shouldCreateMeeting() {

        //Открываем страницу с формой заказа
        open("http://localhost:9999");

        // Генерация даты
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        // Заполнение поля "Город"
        $("[data-test-id=city] input").setValue("Москва");

        // Очистка поля "Дата" перед вводом
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);

        // Заполнение поля "Дата"
        $("[data-test-id='date'] input").setValue(formattedDate);

        // Заполнение поля "Фамилия и Имя"
        $("[name='name']").setValue("Цыбулька Алина");

        // Заполнение поля "Телефон"
        $("[name='phone']").setValue("+79858930397");

        // Установка флажка согалсия
        $("[data-test-id=agreement]").click();

        // Отправка формы
        $("button.button_view_extra").click();

        // Проверка всплывающего окна об успешном завершении
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + formattedDate), Duration.ofSeconds(15))
                .shouldBe(visible);
    }
}
