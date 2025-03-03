package com.example.demo1.service.imp;

import com.example.demo1.db.BookDb;
import com.example.demo1.model.Book;
import com.example.demo1.repository.BookRepository;
import com.example.demo1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBooksById(String id) {
        // C1: Querry trực tiếp từ DB
        return bookRepository.findById(id);

        //C2: Lấy toàn bộ books ra, r tìm kiếm trong list đó
//        List<Book> books = bookRepository.findAll();
//        for (Book book : BookDb.books) { //Select * from books where id =
//            if(book.getId().equals(id)) return book;
//
//        }
//        return null;

    }
}
