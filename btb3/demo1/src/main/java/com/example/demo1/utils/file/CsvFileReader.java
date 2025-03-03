package com.example.demo1.utils.file;

import com.example.demo1.model.Book;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Slf4j
@Component("csvFileReader")
public class CsvFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        log.info("Reading CSV file: {}", filePath);
        try (FileReader reader = new FileReader(filePath)) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
                    .withType(Book.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            log.error("Error reading CSV file: {}", filePath, e);
            return Collections.emptyList();
        }
    }
}
