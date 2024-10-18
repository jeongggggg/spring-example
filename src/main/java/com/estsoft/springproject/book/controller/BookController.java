package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.domain.BookDTO;
import com.estsoft.springproject.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    // 의존성 주입을 위한 생성자
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 책(전체) 조회
    @GetMapping
    public String showAll(Model model) {
        // Book 객체를 BookDTO로 변환
        List<BookDTO> list = bookService.findAll()  // Book 리스트 가져오기
                .stream()
                .map(BookDTO::new)  // 각 Book 객체를 BookDTO로 변환
                .toList();

        // 모델에 변환된 BookDTO 리스트를 추가
        model.addAttribute("bookList", list);
        return "bookManagement";  // bookManagement 템플릿으로 반환
    }

    // 책 단건 조회
    @GetMapping("/{id}")
    public String showOne(@PathVariable String id, Model model){
        Book book = bookService.findBy(id);
        model.addAttribute("book", new BookDTO(book));
        return "bookDetail";
    }
}