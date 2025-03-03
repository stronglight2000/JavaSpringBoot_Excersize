package com.example.demo1.service;

import com.example.demo1.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBooksById(String id);
}
