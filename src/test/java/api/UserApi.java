package api;

import models.UserDeniedResponseModel;
import models.UserResponseModel;


import static io.restassured.RestAssured.given;
import static specs.SpecUser.*;

public class UserApi {
    public static UserResponseModel getInfUser (String token, String userId) {
        return given(userRequest(userId))
                .header("Authorization", "Bearer " + token)
        .when()
                .get()
        .then()
                .log().all()
                .spec(successUserResponse)
                .extract().as(UserResponseModel.class);
    }
    public static UserDeniedResponseModel getInfUserDenied (String token, String userId) {
        return given(userRequest(userId))
                .header("Authorization", "Bearer " + token)
                .when()
                .get()
                .then()
                .log().all()
                .spec(accessIsDeniedUserResponse)
                .extract().as(UserDeniedResponseModel.class);
    }
}
