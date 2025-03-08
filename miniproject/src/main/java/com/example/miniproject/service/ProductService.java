package com.example.miniproject.service;

import com.example.miniproject.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByPage(int page, int size, String keyword);
    int getTotalPages(int size, String keyword);
    Product getProductById(int id);
}
