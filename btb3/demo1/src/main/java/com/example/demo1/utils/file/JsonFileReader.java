package com.example.demo1.utils.file;

import com.example.demo1.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Component("jsonFileReader")
@Slf4j
public class JsonFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String filePath) {
        log.info("Json file");
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            log.error("Error reading JSON file: {}", filePath, e);
            return List.of();
        }
    }
}
