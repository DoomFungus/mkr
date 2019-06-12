package edu.kpi.java.mkr.service;

import edu.kpi.java.mkr.model.Chapter;

import java.util.List;

public interface ChapterService {
    List<Chapter> getChaptersByBook(Number bookId);

    Chapter getChapterWithText(Number chapterId);

    Chapter getCurrentChapter(Number bookId);

    void setCurrentChapter(Number chapterId);
}
