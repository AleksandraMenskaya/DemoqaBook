package tests;

import api.UserApi;
import helpers.WithSession;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

import api.AuthorizationApi;
import api.BooksApi;
import org.openqa.selenium.By;

public class DemoqaBookStoreTest extends TestBase {
    @Test
    @Tag("BooksTestWithUI")
    @DisplayName("Удаление книги через UI")
    @WithSession
    void successfulDeleteBookFromBookStoreTest() {
        LoginResponseModel authResponse = step("Делаем запрос на авторизацию", () ->
                AuthorizationApi.authResponse("AM", "740321748Am!")
        );
        step("Удаляем все книги из Profile", () ->
                BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Добавляем книгу в Profile", () ->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Открываем страницу profile", () ->
                open("/profile")
        );
        step("Кликаем по значку корзины", () -> {
                $(By.xpath("//*[text()='Consent']")).click();
                $("#delete-record-undefined").click();

        });

        step("Соглашаемся с удалением книги", () ->
                $("#closeSmallModal-ok").click()
        );

        step("Проверяем, что книга не отображается в таблице", () ->
                $(".ReactTable").shouldNotHave(text("Git Pocket Guide"))
        );
    }

    @Test
    @Tag("BooksTest")
    @DisplayName("Получение авторизованным пользователем книги, которая есть в коллекции")
    void errorPutBookFromBookStoreTest() {
        LoginResponseModel authResponse = step("Делаем запрос на авторизацию", () ->
                AuthorizationApi.authResponse()
        );
        step("Удаляем все книги из Profile", () ->
                BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );
        step("Добавляем книгу в Profile", () ->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );
        PutErrorBookResponseModel putErrorBookResponse = step("Делаем запрос на получение книг пользователем", () ->
                BooksApi.putErrorBook(authResponse.getToken(), authResponse.getUserId())
        );
        step("Проверяем поля ошибки", () -> {
            assertEquals("1206", putErrorBookResponse.getCode());
            assertEquals("ISBN supplied is not available in User's Collection!", putErrorBookResponse.getMessage());
        });
    }

    @Test
    @Tag("BooksTest")
    @DisplayName("Получение книги неавторизованным пользователем")
    void successfulPutBookFromBookStoreTest() {
        UserDeniedResponseModel userDeniedResponseModel = step("Делаем запрос на получение книг пользователем",  ()->
                UserApi.getInfUserDenied("bad_token", "656dce53-7f16-402b-9378-893d765ce404")
        );
        step("Проверяем поля данных книги", () -> {
            assertEquals("1200",  userDeniedResponseModel.getCode());
            assertEquals("User not authorized!",  userDeniedResponseModel.getMessage());
       });
    }
}
