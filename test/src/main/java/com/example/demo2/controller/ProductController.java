package com.example.demo2.controller;

import com.example.demo2.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ProductController {
    List<Product> products = new ArrayList<>(List.of(
            new Product("1","iPhone 15 pro max","đẹp",5000000,"Apple"),
            new Product("2","iPhone 16 pro max","sang",5100000,"Apple"),
            new Product("3","iPhone 13 pro max","cao cấp",3000000,"Apple"),
            new Product("4","Samsung s25 ultra","vuông văn",3200000,"SamSung"),
            new Product("5","Sony Xperia Mark V","thiết kế ấn tượng",2500000,"Sony")
    ));

    // 1. Lấy danh sách book: GET - /books
    @GetMapping("/products") // HTTP method + API URL
    public List<Product> getAllProducts() {
        return products;
    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
