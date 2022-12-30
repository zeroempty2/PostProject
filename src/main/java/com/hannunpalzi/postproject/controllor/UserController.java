package com.hannunpalzi.postproject.controllor;

import com.hannunpalzi.postproject.dto.SignupRequestDto;
import com.hannunpalzi.postproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/users/signup")
    public void signup (@RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
    }

    @PostMapping("/users/login")
    public void login() {
        userService.login();
    }
}
