package com.hannunpalzi.postproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private long commentId;
    private String writer;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(long commentId, String writer, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
