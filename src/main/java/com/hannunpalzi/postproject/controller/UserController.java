package com.hannunpalzi.postproject.controller;

import com.hannunpalzi.postproject.dto.AdminSignupRequestDto;
import com.hannunpalzi.postproject.dto.LoginRequestDto;
import com.hannunpalzi.postproject.dto.UserSignupRequestDto;
import com.hannunpalzi.postproject.dto.UsernameAndRoleResponseDto;
import com.hannunpalzi.postproject.jwtUtil.JwtUtil;
import com.hannunpalzi.postproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/users/signup")
    public void signup (@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    @PostMapping("/admin/signup")
    public void signupAdmin (@RequestBody AdminSignupRequestDto requestDto) {
        userService.signupAdmin(requestDto);
    }

    @PostMapping("/users/login")
    public void login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        UsernameAndRoleResponseDto responseDto = userService.login(requestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(responseDto.getUsername(), responseDto.getRole()));
    }
}
