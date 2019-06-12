package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.Author;
import lombok.Data;

@Data
public class AuthorDTO {
    @JsonProperty("id")
    private int authorId;
    @JsonProperty("name")
    private String authorName;

    public static AuthorDTO from(Author author){
        AuthorDTO res = new AuthorDTO();
        res.authorId = author.getAuthorId();
        res.authorName = author.getAuthorName();
        return res;
    }
}
