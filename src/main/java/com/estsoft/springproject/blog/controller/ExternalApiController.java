package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
public class ExternalApiController {

    private final ArticleRepository articleRepository;

    public ExternalApiController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/api/external")
    public String callApiAndSave() {
        // 외부 API 호출
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";

        // 외부 API 호출 및 역직렬화
        ResponseEntity<List<Content>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Content>>() {}
        );

        List<Content> externalContents = response.getBody();
        log.info("응답 코드: {}", response.getStatusCode());
        log.info("받은 데이터: {}", externalContents);

        // 외부 데이터를 Article 엔티티로 변환하고 DB에 저장
        if (externalContents != null) {
            for (Content content : externalContents) {
                Article article = Article.builder()
                        .title(content.getTitle())
                        .content(content.getBody())
                        .build();

                // 데이터 저장
                articleRepository.save(article);
            }
            log.info("DB에 {}개의 데이터를 저장했습니다.", externalContents.size());
        }

        return "OK";
    }
}
