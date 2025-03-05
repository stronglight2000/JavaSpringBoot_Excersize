package com.example.demo4.utils.file;

import com.example.demo4.model.People;

import java.util.List;

public interface IFileReader {
    List<People> readFile(String filePath);
}
