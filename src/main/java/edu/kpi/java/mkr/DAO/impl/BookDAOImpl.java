package edu.kpi.java.mkr.DAO.impl;

import edu.kpi.java.mkr.DAO.BookDAO;
import edu.kpi.java.mkr.DAO.mapper.BookMapper;
import edu.kpi.java.mkr.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    public BookDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private final static String GET_ALL_PERMITTED_BOOKS ="SELECT b.book_id, b.book_name, s.series_name, b.series_index, b.creation_date\n" +
            "FROM book b\n" +
            "LEFT JOIN series s on b.series_id = s.series_id\n" +
            "LEFT JOIN book_ban bb on b.book_id = bb.book_id\n" +
            "WHERE bb.region_id NOT IN (\n" +
            "    SELECT region_id\n" +
            "    FROM user_a\n" +
            "    WHERE user_id = ?\n" +
            "    )\n" +
            "OR bb.region_id ISNULL\n" +
            "LIMIT ?\n" +
            "OFFSET ?";

    private final static String GET_ALL_BOOKS_BY_USER = "SELECT b.book_id, b.book_name, s.series_name, b.series_index, b.creation_date\n" +
            "FROM book b\n" +
            "LEFT JOIN series s on b.series_id = s.series_id\n" +
            "JOIN user_book ub on b.book_id = ub.book_id\n" +
            "WHERE ub.user_id = ?\n" +
            "LIMIT ?\n" +
            "OFFSET ?";

    @Override
    public List<Book> getAllPermittedBooks(int userId, Number limit, Number offset) {
        return jdbcTemplate.query(GET_ALL_PERMITTED_BOOKS, new Object[]{userId, limit, offset}, new BookMapper());
    }

    @Override
    public List<Book> getUserBooks(int userId, Number limit, Number offset) {
        return jdbcTemplate.query(GET_ALL_BOOKS_BY_USER, new Object[]{userId, limit, offset}, new BookMapper());
    }
}
