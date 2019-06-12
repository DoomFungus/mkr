package edu.kpi.java.mkr.DAO.impl;

import edu.kpi.java.mkr.DAO.BookDAO;
import edu.kpi.java.mkr.DAO.mapper.BookMapper;
import edu.kpi.java.mkr.model.Author;
import edu.kpi.java.mkr.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {

    @Autowired
    public BookDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    private final static String GET_BOOK_BY_ID = "SELECT b.book_id, b.book_name, s.series_name, b.series_index, b.creation_date\n" +
            "FROM book b\n" +
            "LEFT JOIN series s on b.series_id = s.series_id\n" +
            "WHERE b.book_id = ?";

    private static final String GET_AUTHORS_FOR_BOOKS = "SELECT ba.book_id, a.author_id, a.author_name\n" +
            "    FROM book_author ba\n" +
            "    JOIN author a on ba.author_id = a.author_id\n" +
            "    WHERE ba.book_id IN (:ids)";

    @Override
    public List<Book> getAllPermittedBooks(int userId, Number limit, Number offset) {
        return attachAuthors(jdbcTemplate.query(GET_ALL_PERMITTED_BOOKS, new Object[]{userId, limit, offset}, new BookMapper()));
    }

    @Override
    public List<Book> getUserBooks(int userId, Number limit, Number offset) {
        return attachAuthors(jdbcTemplate.query(GET_ALL_BOOKS_BY_USER, new Object[]{userId, limit, offset}, new BookMapper()));
    }

    @Override
    public Book getBook(Number id) {
        return attachAuthors(jdbcTemplate.queryForObject(GET_BOOK_BY_ID, new Object[]{id}, new BookMapper()));
    }

    private List<Book> attachAuthors(List<Book> books){
        Map<Integer, Book> booksMap = books.stream()
                .collect(Collectors.toMap(Book::getBookId, Function.identity()));
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", booksMap.keySet());
        SqlRowSet result = namedParameterJdbcTemplate.queryForRowSet(GET_AUTHORS_FOR_BOOKS, parameters);
        while(result.next()){
            booksMap.get(result.getInt(1)).getAuthors().add(new Author(result.getInt(2), result.getString(3)));
        }
        return books;
    }

    private Book attachAuthors(Book book){
        List<Book> books = new ArrayList();
        books.add(book);
        return attachAuthors(books).get(0);
    }

}
