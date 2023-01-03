package com.hannunpalzi.postproject.controller;

import com.hannunpalzi.postproject.dto.ReCommentRequestDto;
import com.hannunpalzi.postproject.dto.ReCommentResponseDto;
import com.hannunpalzi.postproject.entity.ReComment;
import com.hannunpalzi.postproject.security.UserDetailsImpl;
import com.hannunpalzi.postproject.service.ReCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReCommentController {
    private final ReCommentService reCommentService;

    //대댓글 작성
    public ReCommentResponseDto createReComment(@PathVariable Long commentId, @RequestBody ReCommentRequestDto reCommentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        String username = userDetails.getUsername();
        return reCommentService.createReComment(commentId, reCommentRequestDto, username);
    }

    //대댓글 수정 (일반유저)
    //대댓글 수정 (관리자)
    //대댓글 삭제 (일반유저)
    //대댓글 삭제 (관리자)

}
