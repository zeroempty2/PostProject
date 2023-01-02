package com.hannunpalzi.postproject.controller;

import com.hannunpalzi.postproject.dto.*;
import com.hannunpalzi.postproject.entity.User;
import com.hannunpalzi.postproject.jwtUtil.JwtUtil;
import com.hannunpalzi.postproject.security.UserDetailsImpl;
import com.hannunpalzi.postproject.service.CommentService;
import com.hannunpalzi.postproject.service.TokenChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public CommentCreatResponseDto createComment(@PathVariable Long postId , @RequestBody CommentRequestDto commentRequestDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return commentService.createComment(postId, commentRequestDto, username);
    }
    // 댓글 수정 (일반)
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public CommentUpdateResponseDto updateComment(@PathVariable Long commentId , @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return commentService.updateComment(commentId, postId, commentRequestDto, username);
    }
    //댓글 수정 (관리자)
    @PutMapping("/admin/posts/{postId}/comments/{commentId}")
    public CommentUpdateResponseDto updateCommentAdmin(@PathVariable Long commentId, @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return commentService.updateCommentAdmin(commentId, postId, commentRequestDto, username);
    }
    // 댓글 삭제 (일반)
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public StatusResponseDto deleteComment(@PathVariable Long commentId, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        return commentService.deleteComment(commentId, postId, username);
    }
    // 댓글 삭제 (관리자)
    @DeleteMapping("/admin/post/{postId}/comments/{commentId}")
    public StatusResponseDto deleteCommentAdmin(@PathVariable Long commentId, @PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        return commentService.deleteCommentAdmin(commentId, postId, username);
    }
}
