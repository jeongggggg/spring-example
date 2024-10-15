package com.estsoft.springproject.blog.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    @Test
    public void testArticle() {
        // 일반 객체 생성 방식
        Article article = new Article("제목", "내용");

        // 빌더를 사용한 객체 생성 (메소드 체이닝이 가능하게 해줌) / 빌더 패턴
        Article articleBuilder = Article.builder()
                .title("제목")
                .content("내용")
                .build();
    }
}