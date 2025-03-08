package com.example.demo1.controller;

import com.example.demo1.model.Book;
import com.example.demo1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo1.db.BookDb.books;

// @RestController = @Controller + @ResponseBody
@RestController // Đánh dấu lên class -> Class này sẽ xử lý request và response (controller)
@RequestMapping("/books")
public class BookController {
    private final BookService bookService; // Bookservice cũng là 1 bean, bookcontroller cũng là 1 bean, controller gọi tới service

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // Ví dụ với controller trả về view
    @GetMapping("/home") // /home -> url
    public String getHome() {
        return "home"; // tên template
    }

    //1. Lấy danh sách Book // Get /books
//    @GetMapping("/books") // HTTP method + API URL
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Lấy book theo id
    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        Book book = bookService.getBooksById(id);
        return ResponseEntity.ok(books);

    }
//    public List<Book> getAllBooks() {
//        return books;
//    }


    //1. Viết API để trả về danh sachs book. Sắp xếp theo năm giảm dần
    //GET: /books/sortByYear
//    @GetMapping("/sortByYear")
//    public ResponseEntity <List<Book>> getBookSortByYear(){
//
//    }

    //2. Viết API để tìm kiếm các cuốn sách mà trong title có chứa keyword, không phân biệt hoa thường
    //GET: /books/search/{keyword}
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> getBookByKeyWord(@PathVariable String keyword) {
        List<Book> rs = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                rs.add(book);
            }
        }
        return ResponseEntity.ok(rs);
    }

    //3. Viết API để tìm kiếm các cuốn sách có year được sản xuất từ năm A -> năm B
    //GET: /books/startYear/{startYear}/endYear/{endYear}
    @GetMapping("/startYear/{startYear}/endYear/{endYear}")
    public ResponseEntity<List<Book>> getBookByYear(@PathVariable int startYear, @PathVariable int endYear) {
        List<Book> rs = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= startYear && book.getYear() <= endYear) {
                {
                    rs.add(book);
                }
            }
        }
        return ResponseEntity.ok(rs);
    }
}
