package edu.kpi.java.mkr.service.impl;

import edu.kpi.java.mkr.DAO.BookDAO;
import edu.kpi.java.mkr.model.Book;
import edu.kpi.java.mkr.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public Book getBook(Number id) {
        return bookDAO.getBook(id);
    }

    @Override
    public List<Book> getAllBooks(Number limit, Number offset) {
        return bookDAO.getAllPermittedBooks(2, limit, offset);
    }
}
