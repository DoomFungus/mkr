package edu.kpi.java.mkr.model;

import lombok.Data;

@Data
public class Chapter {
    private int chapterId;
    private String chapterName;
    private int chapterNumber;
    private String chapterText;
    private Book book;
}
