package com.example.miniproject.repository.impl;

import com.example.miniproject.db.ProductDb;
import com.example.miniproject.model.Product;
import com.example.miniproject.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public List<Product> findAll() {
        return ProductDb.productList;
    }

    @Override
    public Product findById(int id) {
        return ProductDb.productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
