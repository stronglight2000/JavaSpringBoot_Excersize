package com.example.demo1;

import com.example.demo1.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
        Book book = Book.builder()
                .title("Lập trình")
                .year(2021)
                .id("1")
                .build();
        System.out.println(book);

    }

}
