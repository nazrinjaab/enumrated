package org.example.relations.service;

import org.example.relations.entity.Book;
import org.example.relations.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBook(Long id) {

        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            System.out.println("author: " + book.getAuthor().getName());
        }


        return book;
    }


        public void getBooksWithPagination() {

            PageRequest pageRequest = PageRequest.of(
                    0,
                    5,
                    Sort.by("title")
            );

            Page<Book> page = bookRepository.findAll(pageRequest);

            System.out.println("page size: " + page.getTotalPages());

            System.out.println("elements: " + page.getTotalElements());

            System.out.println("about:");

            for (Book book : page.getContent()) {
                System.out.println(book.getTitle());
            }
        }
    }
