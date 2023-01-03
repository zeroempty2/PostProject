package com.hannunpalzi.postproject.service;

import com.hannunpalzi.postproject.dto.ReCommentRequestDto;
import com.hannunpalzi.postproject.dto.ReCommentResponseDto;
import com.hannunpalzi.postproject.entity.Comment;
import com.hannunpalzi.postproject.entity.ReComment;
import com.hannunpalzi.postproject.entity.User;
import com.hannunpalzi.postproject.repository.CommentRepository;
import com.hannunpalzi.postproject.repository.ReCommentRepository;
import com.hannunpalzi.postproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReCommentService {
    private final ReCommentRepository reCommentRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 대댓글 작성하기
    @Transactional
    public ReCommentResponseDto createReComment(Long commentId, ReCommentRequestDto reCommentRequestDto, String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 댓글이 존재하지 않습니다.")
        );
        ReComment reComment = new ReComment(reCommentRequestDto, user, comment);
        reCommentRepository.save(reComment);
        return new ReCommentResponseDto(reComment);
    }
}
