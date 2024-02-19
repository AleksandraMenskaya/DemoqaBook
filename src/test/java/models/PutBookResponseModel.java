package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PutBookResponseModel {
    String userId, username;
    List<IsbnBookModel> collectionOfIsbns;
}
