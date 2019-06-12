package edu.kpi.java.mkr.DAO.impl;

import edu.kpi.java.mkr.DAO.ChapterDAO;
import edu.kpi.java.mkr.DAO.mapper.ChapterMapper;
import edu.kpi.java.mkr.DAO.mapper.ChapterWithTextMapper;
import edu.kpi.java.mkr.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ChapterDAOImpl implements ChapterDAO {

    @Autowired
    public ChapterDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    private final static String SET_CURRENT_CHAPTER = "INSERT into user_book\n" +
            "values (:user_id, (SELECT book_id from chapter where chapter_id = :chapter_id), :chapter_id)\n" +
            "ON CONFLICT (user_id, book_id) DO UPDATE\n" +
            "SET current_chapter = :chapter_id";
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

    @Override
    public void setCurrentChapter(Number chapterId, Number userId){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("user_id", userId);
        parameterSource.addValue("chapter_id", chapterId);
        namedParameterJdbcTemplate.update(SET_CURRENT_CHAPTER, parameterSource);
    }
}
