package org.example.relations.entity;

import jakarta.persistence.*;
import lombok.*;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Entity
    @Table(name = "authors")
    public class Author {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
    }

