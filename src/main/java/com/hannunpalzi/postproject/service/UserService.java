package com.hannunpalzi.postproject.service;

import com.hannunpalzi.postproject.dto.SignupRequestDto;
import com.hannunpalzi.postproject.entity.User;
import com.hannunpalzi.postproject.entity.UserRoleEnum;
import com.hannunpalzi.postproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.getAdminToken() != null) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록할 수 없습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public void login() {

    }
}
