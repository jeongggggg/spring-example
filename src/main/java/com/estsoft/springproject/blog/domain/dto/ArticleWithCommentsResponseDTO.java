package com.estsoft.springproject.blog.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleWithCommentsResponseDTO {
    private Long articleId;
    private String title;
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

    private List<CommentResponseDTO> comments;

    public ArticleWithCommentsResponseDTO(Long articleId, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, List<CommentResponseDTO> comments) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.createAt = createdAt;
        this.updateAt = updatedAt;
        this.comments = comments;
    }
}
