package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void showAll() throws Exception {
        // given :
//        Book mockBook = new Book();
//        doReturn(bookList).when(bookService.findAll());

        // when :
        ResultActions resultActions = mockMvc.perform(get("/books"));

        // then :
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bookManagement")) // (Matchers.is("bookManagement")));
                .andExpect(model().attribute("bookList", hasSize(2)))
        ;
    }
}