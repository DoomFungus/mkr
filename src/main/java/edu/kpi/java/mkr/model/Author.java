package edu.kpi.java.mkr.model;

import lombok.Data;

import java.util.List;

@Data
public class Author {
    private int authorId;
    private String authorName;
    private List<Book> books;
}
