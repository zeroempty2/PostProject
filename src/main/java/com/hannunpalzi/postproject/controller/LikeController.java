package com.hannunpalzi.postproject.controller;

import com.hannunpalzi.postproject.dto.LikeRequestDto;
import com.hannunpalzi.postproject.dto.StatusResponseDto;
import com.hannunpalzi.postproject.security.UserDetailsImpl;
import com.hannunpalzi.postproject.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/posts/{id}/like")
    public ResponseEntity<StatusResponseDto> postLike(@PathVariable Long id, @RequestBody LikeRequestDto likeRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return likeService.postLike(username,id,likeRequestDto);
    }
    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<StatusResponseDto> commentLike(@PathVariable Long commentId, @RequestBody LikeRequestDto likeRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return likeService.commentLike(username,commentId,likeRequestDto);
    }
}