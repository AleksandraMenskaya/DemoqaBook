package tests;

import api.AuthorizationApi;
import api.UserApi;
import models.LoginResponseModel;
import models.UserDeniedResponseModel;
import models.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginDemoqaBookStoreTest extends TestBase {
    @Test
    @Tag("LoginDemoqaBooksTest")
    @DisplayName("Проверка успешной авторизации")
    void successfulLogInTest () {
        LoginResponseModel authResponse = step("Делаем запрос на авторизацию", ()->
                AuthorizationApi.authResponse()
        );
        UserResponseModel userResponseModel = step("Отправляем запрос user", ()->
                UserApi.getInfUser(authResponse.getToken(), authResponse.getUserId())
        );
        step("Проверяем наличие поля userId, userName", () -> {
            assertEquals( "656dce53-7f16-402b-9378-893d765ce404", userResponseModel.getUserId());
            assertEquals("AM", userResponseModel.getUsername());
        });
    }
    @Test
    @Tag("LoginDemoqaBooksTest")
    @DisplayName("Проверка, что не отдается информация о пользователе другому пользователю")
    void successfulAccesssIsDeniedTest () {
        LoginResponseModel authResponse = step("Делаем запрос на авторизацию", ()->
                AuthorizationApi.authResponse("Ivan", "123456Az!")
        );
        UserDeniedResponseModel userDeniedResponseModel = step("Отправляем запрос используя userId другого пользователя", ()->
                UserApi.getInfUserDenied(authResponse.getToken(), "656dce53-7f16-402b-9378-893d765ce404")
        );
        step("Проверяем наличие поля code, message", () -> {
            assertEquals( "1200", userDeniedResponseModel.getCode());
            assertEquals("User not authorized!", userDeniedResponseModel.getMessage());
        });
    }

}
