package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.Author;
import edu.kpi.java.mkr.model.Book;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookDetailsDTO {
    @JsonProperty("id")
    private int bookId;
    @JsonProperty("name")
    private String bookName;
    @JsonProperty("series_name")
    private String seriesName;
    @JsonProperty("series_index")
    private int seriesIndex;
    @JsonProperty("authors")
    private List<AuthorDTO> authors;

    public static BookDetailsDTO from(Book book){
        BookDetailsDTO res = new BookDetailsDTO();
        res.setBookId(book.getBookId());
        res.setBookName(book.getBookName());
        if(book.getSeries() != null) {
            res.setSeriesName(book.getSeries().getSeriesName());
            res.setSeriesIndex(book.getSeriesIndex());
        }
        if(book.getAuthors() != null) {
            res.setAuthors(book.getAuthors()
                    .stream()
                    .map(AuthorDTO::from)
                    .collect(Collectors.toList()));
        }
        return res;
    }
}
