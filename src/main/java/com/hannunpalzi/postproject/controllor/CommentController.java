package com.hannunpalzi.postproject.controllor;

import com.hannunpalzi.postproject.dto.CommentRequestDto;
import com.hannunpalzi.postproject.entity.Comment;
import com.hannunpalzi.postproject.jwtUtil.JwtUtil;
import com.hannunpalzi.postproject.service.CommentService;
import com.hannunpalzi.postproject.service.TokenChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final TokenChecker tokenChecker;

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable Long postId , @RequestBody CommentRequestDto commentRequestDto , HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);
        String username = tokenChecker.checkToken(token);
        return commentService.createComment(postId, commentRequestDto, username);
    }
    // 댓글 수정 (일반)
    @PutMapping("/posts/{postId}/comments/{commentsId}")
    public Long modifiedComment(@PathVariable Long commentsId , @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto){

        return null;
    }
    // 댓글 삭제 (일반)
    @DeleteMapping("/post/{postId}/comments/{commentsId}")
    public Long deleteComment(@PathVariable Long commentsId, HttpServletRequest request, @PathVariable Long postId){
        String token = jwtUtil.resolveToken(request);
        String username = tokenChecker.checkToken(token);
        return commentService.deleteComment(commentsId, username);
    }

}
