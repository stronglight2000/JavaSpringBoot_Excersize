package com.example.demo1.repository.impl;

import com.example.demo1.db.BookDb;
import com.example.demo1.model.Book;
import com.example.demo1.repository.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo1.db.BookDb.books;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll() { // Select * from books
        return BookDb.books;
    }

    @Override
    public Book findById(String id) {
        for (Book book : BookDb.books) { //Select * from books where id =
            if(book.getId().equals(id)) return book;

        }
        return null;
    }
}
