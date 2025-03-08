package com.example.miniproject.repository;

import com.example.miniproject.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
}
