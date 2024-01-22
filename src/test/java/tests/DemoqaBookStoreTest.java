package tests;

import helpers.WithSession;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import api.AuthorizationApi;
import api.BooksApi;
import models.LoginResponseModel;

public class DemoqaBookStoreTest extends TestBase {
    @Test
    @WithSession
    void successfulDeleteBookFromBookStore () {
        LoginResponseModel authResponse = step("Делаем запрос на авторизацию", ()->
                AuthorizationApi.authResponse()
        );

        step("Удаляем все книги из Profile", ()->
                BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Добавляем книгу в Profile", ()->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Открываем страницу profile",  ()->
            open("/profile")
        );

        step("Кликаем по значку корзины",  ()->
            $("#delete-record-undefined").click()
        );

        step("Соглашаемся с удалением книги",  ()->
             $("#closeSmallModal-ok").click()
         );

        step("Проверяем, что книга не отображается в таблице",  ()->
            $(".ReactTable").shouldNotHave(text("Git Pocket Guide"))
        );
    }
}
