package helpers;

import api.AuthorizationApi;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class SessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        step("Открывем вкладку", () -> {
                open("/");
                LoginResponseModel authResponse = AuthorizationApi.authResponse();
                getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
                getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
                getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
            }
        );
    }
}
