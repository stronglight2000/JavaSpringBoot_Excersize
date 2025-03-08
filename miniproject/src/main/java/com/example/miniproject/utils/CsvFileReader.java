package com.example.miniproject.utils;

import com.example.miniproject.db.ProductDb;
import com.example.miniproject.model.Product;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Slf4j
@Component("csvFileReader")
public class CsvFileReader implements IFileReader {
    @Override
    public List<Product> readFile(String filePath) {
        log.info("Reading CSV file: {}", filePath);
        try (FileReader reader = new FileReader(filePath)) {
            CsvToBean<Product> csvToBean = new CsvToBeanBuilder<Product>(reader)
                    .withType(Product.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            log.error("Error reading CSV file: {}", filePath, e);
            return Collections.emptyList();
        }
    }
}
