package api;

import models.LoginBodyModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.Specs.successfulLoginResponse;
import static specs.Specs.loginRequest;


public class AuthorizationApi {

    public static LoginResponseModel authResponse () {

        return authResponse("AM", "740321748Am!");
    }

    public static LoginResponseModel authResponse (String login, String password) {
        LoginBodyModel userData = new LoginBodyModel();
        userData.setUserName(login);
        userData.setPassword(password);

        return
                given(loginRequest)
                        .body(userData)
                .when()
                        .post()
                .then()
                        .log().all()
                        .spec(successfulLoginResponse)
                        .extract().as(LoginResponseModel.class);
    };
}
