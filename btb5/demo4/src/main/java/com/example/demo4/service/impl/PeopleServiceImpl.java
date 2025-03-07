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

    @Override
    public People getPersonById(int id) {
        return (People) peopleRepository.findById(id).orElse(null); // Trả về null nếu không tìm thấy
    }

    @Override
    public List<People> getRelatedPeople(People person) {
        return PeopleDb.peopleList.stream()
                .filter(p -> p.getId() != (person.getId())) // Không trùng ID
                .filter(p -> p.getGender().equals(person.getGender())) // Cùng giới tính
                .sorted(Comparator.comparingInt(People::getId).reversed()) // Sắp xếp ID giảm dần
                .limit(4) // Giới hạn 4 người
                .collect(Collectors.toList());
    }
}
