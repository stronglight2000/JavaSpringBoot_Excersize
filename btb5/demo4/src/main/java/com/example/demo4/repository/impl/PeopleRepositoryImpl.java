package com.example.demo4.repository.impl;

import com.example.demo4.db.PeopleDb;
import com.example.demo4.model.People;
import com.example.demo4.repository.PeopleRepository;
import com.example.demo4.utils.file.CsvFileReader;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {
    private final List<People> peopleList;

    public PeopleRepositoryImpl(CsvFileReader fileReader) {
        this.peopleList = fileReader.readFile("people.csv");
    }

    @Override
    public List<People> findAll() {
        return peopleList;
    }

    @Override
    public Optional<Object> findById(int id) {
        return PeopleDb.peopleList.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .map(person -> (Object) person); // Ép kiểu từ People sang Object
    }
}
