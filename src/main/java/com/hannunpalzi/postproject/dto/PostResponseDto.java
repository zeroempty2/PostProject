package com.hannunpalzi.postproject.dto;

import com.hannunpalzi.postproject.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long postId; // 게시글 id
    private String title; // 게시글 제목
    private String contents; // 게시글 내용
    private String writer; // 게시글 작성자
    private LocalDateTime createdAt; // 게시글 작성시간
    private LocalDateTime modifiedAt; // 게시글 수정시간
    private List<CommentResponseDto> commentResponseDtoList; // 게시글에 달린 댓글 리스트


    public PostResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.writer = post.getWriter();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentResponseDtoList = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList()); // stream.map(comment -> CommentResponseDto  과정 추가 필요)
    //long commentId, String writer, String comment, LocalDateTime createdAt, LocalDateTime modifiedAt
    }
}