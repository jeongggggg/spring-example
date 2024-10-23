package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.Article;
import com.estsoft.springproject.blog.domain.Comment;
import com.estsoft.springproject.blog.domain.dto.CommentRequestDTO;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
   private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public CommentService(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Long articleId, CommentRequestDTO commentRequestDTO) {
        Article article = blogRepository.findById(articleId).orElseThrow();
        return commentRepository.save(new Comment(commentRequestDTO.getBody(), article));
    }

    public Comment findComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElse(new Comment());
    }
}
