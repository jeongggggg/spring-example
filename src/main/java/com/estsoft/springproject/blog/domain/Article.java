package com.estsoft.springproject.blog.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //Article 생성자
    @Builder
    public // 롬복에서 아티클에 대한 필드를 세팅할 수 있는 빌더를 만들어 준다.
    Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
