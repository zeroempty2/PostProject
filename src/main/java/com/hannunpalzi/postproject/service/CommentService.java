package com.hannunpalzi.postproject.service;


import com.hannunpalzi.postproject.dto.CommentModifiedResponseDto;
import com.hannunpalzi.postproject.dto.CommentRequestDto;
import com.hannunpalzi.postproject.dto.CommentResponseDto;
import com.hannunpalzi.postproject.entity.Comment;
import com.hannunpalzi.postproject.entity.User;
import com.hannunpalzi.postproject.entity.Post;
import com.hannunpalzi.postproject.repository.CommentRepository;
import com.hannunpalzi.postproject.repository.PostRepository;
import com.hannunpalzi.postproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    // 코멘트 작성하기
    @Transactional
    public Comment createComment(Long postId, CommentRequestDto commentRequestDto, String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 post가 없습니다.")
        );
        Comment comment = new Comment(commentRequestDto, user, post );
        commentRepository.save(comment);
        return comment;
    }

    // 코멘트 수정하기 (일반)
    @Transactional
    public CommentModifiedResponseDto modifiedComment(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("ID가 존재하지 않습니다.")
        );
        comment.modifiedComment(commentRequestDto);
        return null;
    }

    // 코멘트 삭제하기 (일반)
    @Transactional
    public Long deleteComment(Long commentId, String username){
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
