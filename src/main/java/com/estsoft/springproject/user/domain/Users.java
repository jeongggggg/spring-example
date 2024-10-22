package com.estsoft.springproject.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Users() {

    }

    // 생성자 추가
    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // 사용자의 권한 정보 -> 인가와 관련된 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한 정보를 넣어줌. 컨트롤러 단에서 해당 권한이 있을 경우에만 처리해주면 됨.
        // 예를 들어, "새싹", "주니어", "중니어", "시니어", "관리자"와 같이 등급에 따라 보이는 화면을 다르게 권한을 정의할 수도 있음.
        // ROLE_ADMIN 권한 추가
        return List.of(new SimpleGrantedAuthority("user"), new SimpleGrantedAuthority("ROLE_ADMIN")
        );
    }

    // 아래 메소드들은 인증과 관련된 메소드들
    @Override
    public String getUsername() {
        // 서버 내부적으로는 아이디를 PK로 사용하지만, 사용자가 로그인에 사용할 정보로 email을 반환함.
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부 확인. false로 처리하면 모든 계정이 로그인 불가능.
        // 만약 유저마다 개별적으로 만료 처리를 해야 한다면, 'expired' 관련 컬럼을 추가하거나 로직을 추가하면 됨.
        // 예를 들어, 접속 날짜가 하루 이상 지났다면 만료 처리하는 로직을 추가할 수 있음.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 해당 계정이 잠겨있는지 확인
        return true; // 계정이 잠겨 있지 않음
    }

    // 계정의 비밀번호 정보가 만료되었는지 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호가 만료되지 않음
    }

    // 계정이 활성화 되어 있는지 확인
    @Override
    public boolean isEnabled() {
        return true; // 계정이 활성화됨
    }
}