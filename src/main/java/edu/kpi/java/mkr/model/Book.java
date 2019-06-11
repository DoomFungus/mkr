package edu.kpi.java.mkr.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Book {
    private int bookId;
    private String bookName;
    private Series series;
    private int seriesIndex;
    private List<Author> authors;
    private List<Chapter> chapters;
    private LocalDate creationDate;
}
