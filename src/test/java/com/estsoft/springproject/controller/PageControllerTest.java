package com.estsoft.springproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowPage() throws Exception {
        // when
        mockMvc.perform(get("/thymeleaf/example"))
        // then
                .andExpect(status().isOk()) // HTTP 상태 코드 200 확인
                .andExpect(view().name("examplePage")) // 반환된 View 이름 확인
                .andExpect(model().attributeExists("person")) // Model에 "person" 속성 존재 확인
                .andExpect(model().attributeExists("today")) // Model에 "today" 속성 존재 확인
                .andExpect(model().attribute("person",
                        org.hamcrest.Matchers.hasProperty("name", org.hamcrest.Matchers.is("홍길동")))) // "홍길동" 확인
                .andExpect(model().attribute("person",
                        org.hamcrest.Matchers.hasProperty("age", org.hamcrest.Matchers.is(20)))) // 나이 확인
                .andExpect(model().attribute("person",
                        org.hamcrest.Matchers.hasProperty("hobbies",
                                org.hamcrest.Matchers.contains("달리기", "줄넘기", "복싱", "...")))); // 취미 목록 확인
    }
}