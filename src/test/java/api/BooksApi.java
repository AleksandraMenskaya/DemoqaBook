package api;

import models.*;
import specs.Specs;

import java.util.ArrayList;
import static io.restassured.RestAssured.given;

public class BooksApi {

    static String booksEndPoint = "/BookStore/v1/Books/";


    public static void deleteAllBooks (String token, String userId) {
        given(Specs.requestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
        .when()
                .delete(booksEndPoint)
        .then()
                .log().all()
                .spec(Specs.getResponseSpec(204));
    }

    public static AddBookResponseModel addBooks (String token, String userId, String isbn) {
        ArrayList books = new ArrayList<>();
        books.add(new IsbnBookModel(isbn));
        AddBookBodyModel dataBook= new AddBookBodyModel();
        dataBook.setCollectionOfIsbns(books);
        dataBook.setUserId(userId);

        return given(Specs.requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(dataBook)
        .when()
                .post(booksEndPoint)
        .then()
                .log().all()
                .spec(Specs.getResponseSpec(201))
                .extract().as(AddBookResponseModel.class);
    }
    public static ErrorResponseModel putErrorBook (String token, String userId) {
        String isbn = "9781449325862";
        PutBookBodyModel putBookBodyModel = new PutBookBodyModel();
        putBookBodyModel.setUserId(userId);
        putBookBodyModel.setIsbn(isbn);

        return given(Specs.requestSpec)
                .header("Authorization", "Bearer " + token)
                .when()
                .body(putBookBodyModel)
                .put(booksEndPoint + isbn)
                .then()
                .log().all()
                .spec(Specs.getResponseSpec(400))
                .extract().as(ErrorResponseModel.class);
    }
}
