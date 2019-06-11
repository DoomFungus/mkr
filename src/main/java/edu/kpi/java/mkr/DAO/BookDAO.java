package edu.kpi.java.mkr.DAO;

import edu.kpi.java.mkr.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book>  getAllPermittedBooks(int userId, Number limit, Number offset);

    List<Book> getUserBooks(int userId, Number limit, Number offset);
}
