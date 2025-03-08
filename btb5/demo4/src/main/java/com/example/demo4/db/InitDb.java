package com.example.demo4.db;

import com.example.demo4.model.People;
import com.example.demo4.utils.file.IFileReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
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
        List<People> people = fileReader.readFile("people.csv");
        log.info("People size: {}", people.size());

        PeopleDb.peopleList = people;
    }
}

