package edu.kpi.java.mkr.service;

import edu.kpi.java.mkr.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks(Number limit, Number offset);
}
