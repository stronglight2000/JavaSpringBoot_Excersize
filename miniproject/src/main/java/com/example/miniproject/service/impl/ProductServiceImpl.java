package com.example.miniproject.service.impl;

import com.example.miniproject.db.ProductDb;
import com.example.miniproject.model.Product;
import com.example.miniproject.repository.ProductRepository;
import com.example.miniproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByPage(int page, int size, String keyword) {
        List<Product> filteredProducts = ProductDb.productList.stream()
                .filter(p -> keyword.isEmpty() || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, filteredProducts.size());

        if (fromIndex >= filteredProducts.size()) {
            return List.of();
        }
        return filteredProducts.subList(fromIndex, toIndex);
    }

    @Override
    public int getTotalPages(int size, String keyword) {
        long count = ProductDb.productList.stream()
                .filter(p -> keyword.isEmpty() || p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .count();
        return (int) Math.ceil((double) count / size);
    }

    @Override
    public Product getProductById(int id) {
        return ProductDb.productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
