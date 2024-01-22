package helpers;

import api.AuthorizationApi;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";

        System.out.println("________________favicon start ");


        open("/favicon.ico");

        System.out.println("________________ favicon end");
        LoginResponseModel authResponse = AuthorizationApi.authResponse();
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
    }


}
