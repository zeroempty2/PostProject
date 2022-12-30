package com.hannunpalzi.postproject.service;

import com.hannunpalzi.postproject.dto.AdminSignupRequestDto;
import com.hannunpalzi.postproject.dto.LoginRequestDto;
import com.hannunpalzi.postproject.dto.UserSignupRequestDto;
import com.hannunpalzi.postproject.dto.UsernameAndRoleResponseDto;
import com.hannunpalzi.postproject.entity.User;
import com.hannunpalzi.postproject.entity.UserRoleEnum;
import com.hannunpalzi.postproject.jwtUtil.JwtUtil;
import com.hannunpalzi.postproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public void signupAdmin(AdminSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
        if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
            throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록할 수 없습니다.");
        }
        UserRoleEnum role = UserRoleEnum.ADMIN;

        User user = new User(username, password, role);
        userRepository.save(user);
    }

    public UsernameAndRoleResponseDto login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 아이디가 없습니다.")
        );
        if (!user.isvalidPassword(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new UsernameAndRoleResponseDto(username, user.getRole());
    }
}