package edu.kpi.java.mkr.service.impl;

import edu.kpi.java.mkr.DAO.BookDAO;
import edu.kpi.java.mkr.model.Book;
import edu.kpi.java.mkr.service.BookService;
import edu.kpi.java.mkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;
    private UserService userService;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO, UserService userService)
    {
        this.bookDAO = bookDAO;
        this.userService = userService;
    }

    @Override
    public Book getBook(Number id) {
        return bookDAO.getBook(id);
    }

    @Override
    public List<Book> getAllBooks(Number limit, Number offset) {
        int userId = userService.findUser().getUserId();
        return bookDAO.getAllPermittedBooks(userId, limit, offset);
    }
}
