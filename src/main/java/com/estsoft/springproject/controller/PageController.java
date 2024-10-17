package com.estsoft.springproject.controller;

import jakarta.persistence.Convert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {
    // Person   GET /thymeleaf/example
    @GetMapping("/thymeleaf/example") // Controller
    public String show(Model model){ // Model
        Person person = new Person();
        person.setId(1L);
        person.setName("홍길동");
        person.setAge(20);
        person.setHobbies(Arrays.asList("달리기","줄넘기","복싱","..."));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage"; // View, html 페이지
    }
}
