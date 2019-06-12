package edu.kpi.java.mkr.service.impl;

import edu.kpi.java.mkr.DAO.ChapterDAO;
import edu.kpi.java.mkr.model.Chapter;
import edu.kpi.java.mkr.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    ChapterDAO chapterDAO;

    @Autowired
    public ChapterServiceImpl(ChapterDAO chapterDAO) {
        this.chapterDAO = chapterDAO;
    }

    @Override
    public List<Chapter> getChaptersByBook(Number bookId) {
        return chapterDAO.getChaptersByBook(bookId);
    }

    @Override
    public Chapter getChapterWithText(Number chapterId) {
        return chapterDAO.getChapterWithText(chapterId);
    }

    @Override
    public Chapter getCurrentChapter(Number bookId) {
        int userId = 2;
        return chapterDAO.getCurrentChapter(bookId, userId);
    }

    @Override
    public void setCurrentChapter(Number chapterId) {
        int userId = 2;
        chapterDAO.setCurrentChapter(chapterId, userId);
    }
}
