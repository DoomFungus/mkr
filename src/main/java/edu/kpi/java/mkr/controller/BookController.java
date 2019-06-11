package edu.kpi.java.mkr.controller;

import edu.kpi.java.mkr.controller.DTO.BookDetailsDTO;
import edu.kpi.java.mkr.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    private BookService bookService;

    @GetMapping
    public List<BookDetailsDTO> getAllBooks(@RequestParam("limit") Number limit, @RequestParam("offset") Number offset){
        return bookService.getAllBooks(limit, offset)
                .stream()
                .map(BookDetailsDTO::from)
                .collect(Collectors.toList());
    }
}