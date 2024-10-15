package com.estsoft.springproject.blog.controller;


import com.estsoft.springproject.blog.domain.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로깅 어노테이션
@RestController // REST API를 만들기 위한 RESTController 선언
public class BlogController {
    private final BlogService service;

    public BlogController(BlogService service) {
        this.service = service;
    }
    // RequestMapping (특정 url   POST/articles) 글을 작성해서  db에 저장하고 싶기 때문에 post
    // @RequestMapping(method = RequestMethod.POST) => PostMapping 과 같음
    @PostMapping("/articles")
    public ResponseEntity<Article> writeArticle(@RequestBody AddArticleRequest request){
        Article article = service.saveArticle(request);
        // System.out.println(request.getTitle());
        // System.out.println(request.getContent());
        // return ResponseEntity.status(HttpStatus.OK).build(); // 2xx

        // logging
        // 로깅 레벨은 trace, debug, info, warn, error
        log.debug("{}, {}",request.getTitle(), request.getContent());

        // CREATED : 성공 201, 요청으로 인해서 리소스가 생성이 되었다는 의미
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article);

        // RequestBody로 받으면 객체로 json안에 들어있는 각각의 속성들이 그 값에 맞게 들어간다. title, content로 정의 되어 있는 것들을 특정 값으로 파싱해주는 역할을 하는 어노테이션
    }
}