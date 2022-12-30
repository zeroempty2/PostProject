package com.hannunpalzi.postproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String Comment;



    public CommentRequestDto(String comment) {
        Comment = comment;
    }

}
