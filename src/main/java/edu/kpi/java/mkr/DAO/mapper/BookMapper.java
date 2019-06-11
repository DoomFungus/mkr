package edu.kpi.java.mkr.DAO.mapper;

import edu.kpi.java.mkr.model.Book;
import edu.kpi.java.mkr.model.Series;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book b = new Book();
        Series s = new Series();
        b.setBookId(resultSet.getInt(1));
        b.setBookName(resultSet.getString(2));
        s.setSeriesName(resultSet.getString(3));
        b.setSeries(s);
        b.setSeriesIndex(resultSet.getInt(4));
        b.setCreationDate(resultSet.getDate(5).toLocalDate());
        return b;
    }
}
