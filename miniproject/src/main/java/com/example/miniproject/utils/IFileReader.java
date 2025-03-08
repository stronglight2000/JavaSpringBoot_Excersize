package com.example.miniproject.utils;

import com.example.miniproject.model.Product;

import java.util.List;

public interface IFileReader {
    List<Product> readFile(String filePath);
}
