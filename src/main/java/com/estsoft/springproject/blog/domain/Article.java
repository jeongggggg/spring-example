package com.estsoft.springproject.blog.domain;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column
    private LocalDateTime createdAt; // created_at

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt; // updated_at

    //Article 생성자
    @Builder
    public // 롬복에서 아티클에 대한 필드를 세팅할 수 있는 빌더를 만들어 준다.
    Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse convert(){
        return new ArticleResponse(id, title, content);
    }

    public void update(String title, String content){
//        if(!title.isBlank()) { this.title = title; }
//        if(!content.isBlank()) { this.title = title; }
        this.title = title;
        this.content = content;
    }

    // Comment와 1:N 관계를 설정
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
