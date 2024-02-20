package api;


import models.*;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import static specs.SpecBooks.*;

public class BooksApi {
    public static void deleteAllBooks (String token, String userId) {
        given(booksRequest)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
        .when()
                .delete()
        .then()
                .log().all()
                .spec(successDeleteAllBooksResponse);
    }

    public static AddBookResponseModel addBooks (String token, String userId) {
        ArrayList books = new ArrayList<>();
        books.add(new IsbnBookModel("9781449325862"));
        AddBookBodyModel dataBook= new AddBookBodyModel();
        dataBook.setCollectionOfIsbns(books);
        dataBook.setUserId(userId);

        return given(booksRequest)
                .header("Authorization", "Bearer " + token)
                .body(dataBook)
        .when()
                .post()
        .then()
                .log().all()
                .spec(successAddBooksResponse)
                .extract().as(AddBookResponseModel.class);
    }
    public static PutErrorBookResponseModel putErrorBook (String token, String userId) {
        String isbn = "9781449325862";
        PutBookBodyModel putBookBodyModel = new PutBookBodyModel();
        putBookBodyModel.setUserId(userId);
        putBookBodyModel.setIsbn(isbn);

        return given(booksPutRequest(isbn))
                .header("Authorization", "Bearer " + token)
                .when()
                .body(putBookBodyModel)
                .put()
                .then()
                .log().all()
                .spec(errorPutBooksResponse)
                .extract().as(PutErrorBookResponseModel.class);
    }
}
