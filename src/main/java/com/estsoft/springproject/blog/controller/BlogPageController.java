package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springproject.blog.service.BlogService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {
    private final BlogService blogService;

    // BlogService DI(의존성 주입)
    public BlogPageController(BlogService blogService) {
        this.blogService = blogService;
    }

    // GET /articles -> 게시글 목록 페이지 리턴
    @GetMapping("/articles")
    public String showArticle(Model model) {
        // 전체 게시글 리스트 조회
        List<Article> articleList = blogService.findAll();

        // Article 객체 리스트를 ArticleViewResponse 객체 리스트로 변환
        List<ArticleViewResponse> list = articleList.stream()
                .map(ArticleViewResponse::new)
                .toList();

        // 모델에 'articles' 이름으로 게시글 리스트 추가
        model.addAttribute("articles", list);

        // articleList.html 페이지로 이동
        return "articleList";
    }

    // GET /articles/{id} -> 게시글 상세 페이지 리턴
    @GetMapping("/articles/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Principal principal = (Principal) authentication.getPrincipal(); // 주석 처리된 코드

        // 게시글 ID로 게시글 조회
        Article article = blogService.findById(id);

        // 모델에 'article' 이름으로 조회한 게시글 추가
        model.addAttribute("article", new ArticleViewResponse(article));

        // article.html 페이지로 이동
        return "article";
    }

    // GET /new-article?id=1 -> 새 게시글 작성 또는 기존 게시글 수정 페이지 리턴
    @GetMapping("/new-article")
    public String addArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // id 값이 없으면 새로운 게시글 생성
            // 빈 ArticleViewResponse 객체를 모델에 추가
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id 값이 있으면 기존 게시글 수정
            // 게시글 ID로 게시글 조회
            Article article = blogService.findById(id);
            // 조회한 게시글을 ArticleViewResponse 객체로 변환하여 모델에 추가
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        // newArticle.html 페이지로 이동
        return "newArticle";
    }
}