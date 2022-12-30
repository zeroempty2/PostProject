package com.hannunpalzi.postproject.repository;

import com.hannunpalzi.postproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
