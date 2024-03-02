package api;

import models.LoginBodyModel;
import models.LoginResponseModel;
import specs.Specs;

import static io.restassured.RestAssured.given;


public class AuthorizationApi {

    private final static String loginEndPoint = "/Account/v1/Login/";

    public static LoginResponseModel authResponse () {

        return authResponse("AM", "740321748Am!");
    }

    public static LoginResponseModel authResponse (String login, String password) {
        LoginBodyModel userData = new LoginBodyModel();
        userData.setUserName(login);
        userData.setPassword(password);

        return
                given(Specs.requestSpec)
                        .body(userData)
                .when()
                        .post(loginEndPoint)
                .then()
                        .log().all()
                        .spec(Specs.getResponseSpec(200))
                        .extract().as(LoginResponseModel.class);
    };
}
