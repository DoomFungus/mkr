package edu.kpi.java.mkr.DAO.impl;

import edu.kpi.java.mkr.DAO.ChapterDAO;
import edu.kpi.java.mkr.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ChaterDAOImpl implements ChapterDAO {
    @Autowired
    public ChaterDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Chapter> getChaptersByBook(Number bookId) {
        return null;
    }

    @Override
    public Chapter getChapterWithText(Number chapterId) {
        return null;
    }
}
