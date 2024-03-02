package api;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.UserResponseModel;
import models.ErrorResponseModel;
import specs.Specs;

import static io.restassured.RestAssured.given;

public class UserApi {

    private static ValidatableResponse prepareGetRequest(String token, String userId){
        return given(Specs.requestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/"+userId)
                .then()
                .log().all();
    }

    public static UserResponseModel getInfUser (String token, String userId) {
        return prepareGetRequest(token, userId)
            .spec(Specs.getResponseSpec(200))
            .extract().as(UserResponseModel.class);
    }
    public static ErrorResponseModel getInfUserDenied (String token, String userId) {
        return prepareGetRequest(token, userId)
            .spec(Specs.getResponseSpec(401))
            .extract().as(ErrorResponseModel.class);
    }
}
