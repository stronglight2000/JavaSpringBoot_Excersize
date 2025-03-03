package com.example.demo1.db;


import com.example.demo1.model.Book;
import com.example.demo1.utils.file.IFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class InitDb implements CommandLineRunner {
    private final IFileReader fileReader;

    public InitDb(@Qualifier("excelFileReader") IFileReader fileReader) {
        this.fileReader = fileReader;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Book> books = fileReader.readFile("books.xlsx");
        log.info("Books size: {}", books.size());

        BookDb.books = books;
    }
}
