package edu.kpi.java.mkr.model;

import lombok.Data;

import java.util.List;

@Data
public class Series {
    private int seriesId;
    private String seriesName;
    private List<Book> books;
}
