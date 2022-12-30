package com.hannunpalzi.postproject.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String adminToken;
}
