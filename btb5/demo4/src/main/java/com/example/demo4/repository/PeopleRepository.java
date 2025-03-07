package com.example.demo4.repository;

import com.example.demo4.model.People;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PeopleRepository {
    List<People> findAll();

    Optional<Object> findById(int id);
}
