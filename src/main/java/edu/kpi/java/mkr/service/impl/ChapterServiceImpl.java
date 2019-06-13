package edu.kpi.java.mkr.service.impl;

import edu.kpi.java.mkr.DAO.ChapterDAO;
import edu.kpi.java.mkr.model.Chapter;
import edu.kpi.java.mkr.service.ChapterService;
import edu.kpi.java.mkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    private ChapterDAO chapterDAO;
    private UserService userService;

    @Autowired
    public ChapterServiceImpl(ChapterDAO chapterDAO, UserService userService) {
        this.chapterDAO = chapterDAO;
        this.userService = userService;
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
        int userId = userService.findUser().getUserId();
        return chapterDAO.getCurrentChapter(bookId, userId);
    }

    @Override
    public void setCurrentChapter(Number chapterId) {
        int userId = userService.findUser().getUserId();
        chapterDAO.setCurrentChapter(chapterId, userId);
    }
}
