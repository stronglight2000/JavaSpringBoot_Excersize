package com.example.demo4.service;


import com.example.demo4.model.People;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PeopleService {
    List<People> getAllPeople();

    Map<String, List<People>> groupPeopleByCity();

    Map<String, Long> groupJobByCount();

    List<People> aboveAverageSalary();

    Optional<People> longestName();

    People getPersonById(int id);

    List<People> getRelatedPeople(People person);
}
