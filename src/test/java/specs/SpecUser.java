package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class SpecUser {
    static String userEndPoint = "/Account/v1/User/";

    public static RequestSpecification userRequest(String userId) {
        return with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON)
                .basePath(userEndPoint + userId);
    }
    public static ResponseSpecification successUserResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
    public static ResponseSpecification accessIsDeniedUserResponse = new ResponseSpecBuilder()
        .expectStatusCode(401)
        .log(STATUS)
        .log(BODY)
        .build();
}

