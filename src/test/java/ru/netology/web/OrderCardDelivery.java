package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class OrderCardDelivery {
//    private WebDriver driver;
//
//    @BeforeEach
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }

    @Test
    public void shouldCreateMeeting() throws InterruptedException {

        //Открываем страницу с формой заказа
        open("http://localhost:9999");

        // Генерация даты
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);

        //Заполнение поля "Город"
        $("[data-test-id=city] input").setValue("Москва");

        // Заполнение поля "Дата"
        $("[data-test-id=date] input").setValue(formattedDate);

        // Заполнение поля "Фамилия и Имя"
        $("[name='name']").setValue("Цыбулька Алина");

        // Заполнение поля "Телефон"
        $("[name='phone']").setValue("+79858930397");

        // Установка флажка согалсия
        $(("[data-test-id=agreement]")).click();

        // Отправка формы
        $("button.button_view_extra").click();

        // Проверка состояния загрузки
        Thread.sleep(10000);

        // Проверка всплывающего  окна об успешном завершении бронирования
        $(withText("Успешно!")).shouldBe(visible);

    }
}