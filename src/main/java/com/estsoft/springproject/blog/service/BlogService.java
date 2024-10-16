package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // blog 게시글 조회
    public List<Article> findAll(){
        // List<Article> articleList = blogRepository.findAll();
        return blogRepository.findAll();
    }

    // blog 게시글 단건 조회
    public Article findById(Long id){
        // Optional.orElse
        //return blogRepository.findById(id).orElse(new Article()); 다른 방법
        // return blogRepository.findById(id).orElseGet(Article::new); 다른 방법
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id is not found : " + id));
    }

    // blog 게시글 삭제 API (id)
    public void deleteBy(Long id){
        blogRepository.deleteById(id); // pk 값을 찾아서 삭제할 수 있도록
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = findById(id);  // 수정하고자 하는 row (article 객체) 가져오기
        article.update(request.getTitle(),request.getContent());
        return article;
    }
}
