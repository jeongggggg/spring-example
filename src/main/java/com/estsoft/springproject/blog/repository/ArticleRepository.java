package com.estsoft.springproject.blog.repository;

import com.estsoft.springproject.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
