package com.example.demo4.repository;

import com.example.demo4.model.People;

import java.util.List;

public interface PeopleRepository {
    List<People> findAll();
}
