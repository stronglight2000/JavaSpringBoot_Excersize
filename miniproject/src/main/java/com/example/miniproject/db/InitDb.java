package com.example.miniproject.db;

import com.example.miniproject.model.Product;
import com.example.miniproject.utils.IFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Slf4j
@Configuration
public class InitDb implements CommandLineRunner {
    private final IFileReader fileReader;

    public InitDb(@Qualifier("csvFileReader") IFileReader fileReader) {
        this.fileReader = fileReader;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Start init data");
        List<Product> products = fileReader.readFile("product.csv");
        log.info("Product size: {}", products.size());

        ProductDb.productList = products;
    }
}
