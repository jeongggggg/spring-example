package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.domain.BookDTO;
import com.estsoft.springproject.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 책 관련 요청을 처리하는 컨트롤러 클래스
@Controller
@RequestMapping("/books") // "/books" 경로로 들어오는 요청을 처리
public class BookController {
    private final BookService bookService;

    // 의존성 주입을 위한 생성자
    public BookController(BookService bookService) {
        this.bookService = bookService; // BookService 인스턴스를 주입받아 필드에 저장
    }

    // 책(전체) 목록 조회 GET /books
    @GetMapping
    public String showAll(Model model) {
        // 모든 책 정보를 가져와 BookDTO 리스트로 변환
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
    public String showOne(@PathVariable String id, Model model) {
        // 특정 ID로 책 정보 조회
        Book book = bookService.findBy(id);
        // 조회한 Book 객체를 BookDTO로 변환하여 모델에 추가
        model.addAttribute("book", new BookDTO(book));
        return "bookDetail"; // bookDetail 템플릿으로 반환
    }

    // 책 정보 생성 (id, name, author 정보 받아서 DB에 저장)
    // 저장된 책 정보가 바로 노출될 수 있도록 화면 구성 (bookManagement.html)
    @PostMapping
    public String addBook(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String author) {

        // 새 책 정보를 DB에 저장
        bookService.saveOne(new Book(id, name, author));
        return "redirect:/books"; // 책 목록 페이지로 리다이렉트
    }
}