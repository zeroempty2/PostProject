package com.hannunpalzi.postproject.repository;

import com.hannunpalzi.postproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
