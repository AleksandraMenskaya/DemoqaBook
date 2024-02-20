package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class SpecBooks {
    static String booksEndPoint = "/BookStore/v1/Books/";
    public static RequestSpecification booksRequest = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .basePath(booksEndPoint);
    public static ResponseSpecification successDeleteAllBooksResponse = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification successAddBooksResponse = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification booksPutRequest (String isbn) {
        return with()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .contentType(JSON)
                .basePath(booksEndPoint + isbn);
    }
    public static ResponseSpecification errorPutBooksResponse = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();
}

