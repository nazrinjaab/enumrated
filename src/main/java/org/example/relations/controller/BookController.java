package org.example.relations.controller;

import org.example.relations.entity.Book;
import org.example.relations.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }
    @GetMapping("/page")
    public String getBooksPage() {

        bookService.getBooksWithPagination();

        return "about book";
    }
}