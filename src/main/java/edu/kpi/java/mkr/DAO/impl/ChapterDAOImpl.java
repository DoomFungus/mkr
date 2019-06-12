package edu.kpi.java.mkr.DAO.impl;

import edu.kpi.java.mkr.DAO.ChapterDAO;
import edu.kpi.java.mkr.DAO.mapper.ChapterMapper;
import edu.kpi.java.mkr.DAO.mapper.ChapterWithTextMapper;
import edu.kpi.java.mkr.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ChapterDAOImpl implements ChapterDAO {

    @Autowired
    public ChapterDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    private final static String GET_CHAPTERS_BY_BOOK = "SELECT c.chapter_id, c.chapter_name, c.chapter_number, c.book_id\n" +
            "FROM chapter c\n" +
            "WHERE c.book_id = ?";

    private final static String GET_CHAPTER_WITH_TEXT = "SELECT c.chapter_id, c.chapter_name, c.chapter_number, c.book_id, c.chapter_text\n" +
            "FROM chapter c\n" +
            "WHERE c.chapter_id = ?";

    private final static String GET_CURRENT_CHAPTER = "SELECT c.chapter_id, c.chapter_name, c.chapter_number, c.book_id, c.chapter_text\n" +
            "FROM user_book ub\n" +
            "JOIN chapter c on ub.current_chapter = c.chapter_id\n" +
            "WHERE ub.book_id = ?\n" +
            "AND ub.user_id = ?";

    @Override
    public List<Chapter> getChaptersByBook(Number bookId) {
        return jdbcTemplate.query(GET_CHAPTERS_BY_BOOK, new Object[]{bookId}, new ChapterMapper());
    }

    @Override
    public Chapter getChapterWithText(Number chapterId) {
        return jdbcTemplate.queryForObject(GET_CHAPTER_WITH_TEXT, new Object[]{chapterId}, new ChapterWithTextMapper());
    }

    @Override
    public Chapter getCurrentChapter(Number bookId, Number userId) {
        return jdbcTemplate.queryForObject(GET_CURRENT_CHAPTER, new Object[]{bookId, userId}, new ChapterWithTextMapper());
    }
}
