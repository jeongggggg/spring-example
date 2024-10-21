package com.estsoft.springproject.user.service;

import com.estsoft.springproject.user.domain.Users;
import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    // 비밀번호 암호화 인코더
    private final BCryptPasswordEncoder encoder;

    // 생성자 주입
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    // 회원 가입 처리 (비즈니스 로직)
    public Users save(AddUserRequest dto) {
        // DTO로 받은 비밀번호를 암호화하고, Users 엔티티로 저장
        String password = dto.getPassword();
        String email = dto.getEmail();
        String encodedPassword = encoder.encode(password);

        Users users = new Users(email, encodedPassword);
        return userRepository.save(users);

        // 위 코드를 한 줄로 처리하는 경우
//        return userRepository.save(
//                new Users(dto.getEmail(),
//                encoder.encode(dto.getPassword())
//                ));
    }
}