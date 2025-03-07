package com.example.demo4.service.impl;

import com.example.demo4.db.PeopleDb;
import com.example.demo4.model.People;
import com.example.demo4.repository.PeopleRepository;
import com.example.demo4.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class PeopleServiceImpl implements PeopleService {
    private PeopleRepository peopleRepository;

    @Autowired
    public void PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public List<People> getAllPeople() {
        return PeopleDb.peopleList;
    }

    @Override
    public Map<String, List<People>> groupPeopleByCity() {
        return peopleRepository.findAll().stream()
                .collect(Collectors.groupingBy(People::getCity));
    }

    @Override
    public Map<String, Long> groupJobByCount() {
        return peopleRepository.findAll().stream()
                .collect(Collectors.groupingBy(People::getJob, Collectors.counting()));
    }

    @Override
    public List<People> aboveAverageSalary() {
        double avgSalary = peopleRepository.findAll().stream()
                .mapToInt(People::getSalary)
                .average()
                .orElse(0);
        return peopleRepository.findAll().stream()
                .filter(p -> p.getSalary() > avgSalary)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<People> longestName() {
        return peopleRepository.findAll().stream()
                .max(Comparator.comparingInt(p -> p.getFullname().length()));
    }
}
