package edu.kpi.java.mkr.controller;

import edu.kpi.java.mkr.controller.DTO.BookDetailsDTO;
import edu.kpi.java.mkr.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<BookDetailsDTO> getAllBooks(@RequestParam("limit") Number limit, @RequestParam("offset") Number offset){
        return bookService.getAllBooks(limit, offset)
                .stream()
                .map(BookDetailsDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public BookDetailsDTO getBook(@PathVariable("id") Number id){
        return BookDetailsDTO.from(bookService.getBook(id));
    }
}
