package org.example.relations.service;

import org.example.relations.entity.Author;
import org.example.relations.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

}