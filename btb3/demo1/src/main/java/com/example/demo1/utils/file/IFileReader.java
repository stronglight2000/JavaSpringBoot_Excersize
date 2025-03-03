package com.example.demo1.utils.file;

import com.example.demo1.model.Book;

import java.util.List;

public interface IFileReader {
    List<Book> readFile(String filePath);
}
