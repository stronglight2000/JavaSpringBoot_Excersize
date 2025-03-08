package com.example.demo1.repository;

import com.example.demo1.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Book findById(String id);
}
