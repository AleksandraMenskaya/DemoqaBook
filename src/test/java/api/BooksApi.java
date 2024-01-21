package api;


import models.*;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

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
}
