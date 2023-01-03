package com.hannunpalzi.postproject.dto;

import com.hannunpalzi.postproject.entity.User;
import lombok.Getter;

@Getter
public class UserDeleteRequestDto {

    private final String username;
    private final String password;

    public UserDeleteRequestDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
