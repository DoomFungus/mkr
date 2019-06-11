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
    @JsonProperty("author_names")
    private List<String> authorNames;
    @JsonProperty("creation_date")
    private LocalDate creationDate;

    public static BookDetailsDTO from(Book book){
        BookDetailsDTO res = new BookDetailsDTO();
        res.setBookId(book.getBookId());
        res.setBookName(book.getBookName());
        if(book.getSeries() != null) {
            res.setSeriesName(book.getSeries().getSeriesName());
            res.setSeriesIndex(book.getSeriesIndex());
        }
        if(book.getAuthors() != null) {
            res.setAuthorNames(book.getAuthors()
                    .stream()
                    .map(Author::getAuthorName)
                    .collect(Collectors.toList()));
        }
        res.setCreationDate(book.getCreationDate());
        return res;
    }
}
