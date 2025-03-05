package com.example.demo4.controller;

import com.example.demo4.model.People;
import com.example.demo4.service.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class PeopleController {
    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/groupPeopleByCity")
    public String groupPeopleByCity(Model model) {
        Map<String, java.util.List<People>> groupedPeople = peopleService.groupPeopleByCity();
        model.addAttribute("groupedPeople", groupedPeople);
        return "groupPeopleByCity";
    }

    @GetMapping("/groupJobByCount")
    public String groupJobByCount(Model model) {
        Map<String, Long> jobCount = peopleService.groupJobByCount();
        model.addAttribute("jobCount", jobCount);
        return "groupJobByCount";
    }

    @GetMapping("/aboveAverageSalary")
    public String aboveAverageSalary(Model model) {
        model.addAttribute("people", peopleService.aboveAverageSalary());
        return "aboveAverageSalary";
    }

    @GetMapping("/longestName")
    public String longestName(Model model) {
        model.addAttribute("longestName", peopleService.longestName().orElse(null));
        return "longestName";
    }
}
