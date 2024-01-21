package models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddBookBodyModel {
    List<IsbnBookModel> collectionOfIsbns;
    String  userId;

}
