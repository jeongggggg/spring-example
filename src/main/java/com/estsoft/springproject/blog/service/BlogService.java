package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository; // 저장을 위한 의존성 주입(생성자 주입 방식)

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    // blog 게시글 저장
    // repository.save(Article)
    public Article saveArticle(AddArticleRequest request){
        return blogRepository.save(request.toEntity()); // toEntity를 호출해주면 컨버팅 해준 형태로 세이브 메소드에 아티클 객체 형태로 넘겨줌
        // article 형태로 리턴해줄 것이기 때문에 그대로 return
    }
}
