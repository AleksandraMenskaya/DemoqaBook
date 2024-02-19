package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsbnBookModel {
    public IsbnBookModel(String isbn) {
        this.isbn = isbn;
    }
    private String isbn;
    private String title;
    private String subTitle;
    private String publish_date;
    private String publisher;
    private String pages;
    private String description;
    private String website;
}
