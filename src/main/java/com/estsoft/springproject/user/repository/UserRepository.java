package com.estsoft.springproject.user.repository;

import com.estsoft.springproject.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    // Users 엔티티에서 PK(아이디 값)로 조회하는 것이 아닌 이메일로 조회하는 메소드
    // SQL: select * from users where email = ${email};
    // Spring Security에서 사용될 수 있는 유저 정보를 가져오는 메소드 생성
    Optional<Users> findByEmail(String email);
}