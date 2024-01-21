package api;

import models.LoginBodyModel;
import models.LoginResponseModel;

import static io.restassured.RestAssured.given;
import static specs.Specs.successfulLoginResponse;
import static specs.Specs.loginRequest;


public class AuthorizationApi {

    public static LoginResponseModel authResponse () {
        LoginBodyModel userData = new LoginBodyModel();
        userData.setUserName("AM");
        userData.setPassword("740321748Am!");

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
