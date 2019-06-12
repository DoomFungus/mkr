package edu.kpi.java.mkr.DAO.mapper;

import edu.kpi.java.mkr.model.Book;
import edu.kpi.java.mkr.model.Chapter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChapterMapper implements RowMapper<Chapter> {
    @Override
    public Chapter mapRow(ResultSet resultSet, int i) throws SQLException {
        Chapter c = new Chapter();
        Book b = new Book();
        c.setChapterId(resultSet.getInt(1));
        c.setChapterName(resultSet.getString(2));
        c.setChapterNumber(resultSet.getInt(3));
        b.setBookId(resultSet.getInt(4));
        c.setBook(b);
        return c;
    }
}
