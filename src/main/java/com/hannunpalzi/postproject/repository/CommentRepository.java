package com.hannunpalzi.postproject.repository;

import com.hannunpalzi.postproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
