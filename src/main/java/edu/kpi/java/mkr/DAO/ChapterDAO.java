package edu.kpi.java.mkr.DAO;

import edu.kpi.java.mkr.model.Chapter;

import java.util.List;

public interface ChapterDAO {
    List<Chapter> getChaptersByBook(Number bookId);

    Chapter getChapterWithText(Number chapterId);

    Chapter getCurrentChapter(Number bookId, Number userId);

    void setCurrentChapter(Number chapterId, Number userId);
}
