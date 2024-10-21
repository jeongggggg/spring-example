package com.estsoft.springproject.user.service;

import com.estsoft.springproject.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository; // 레포지토리 주입

    // 생성자 주입 (DI)
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 이메일(username)로 사용자 정보를 조회하고, 없을 경우 IllegalStateException 발생
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalStateException(username));
    }
}