package com.estsoft.springproject.user.config;

import com.estsoft.springproject.user.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

// 스프링 시큐리티 설정을 위한 클래스
@Configuration
public class WebSecurityConfiguration {
    private final UserDetailService userDetailService;

    // 생성자 주입
    public WebSecurityConfiguration(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    // 특정 요청을 스프링 시큐리티 필터에서 제외하기 위한 설정
    @Bean
    public WebSecurityCustomizer ignore() {
        // H2 콘솔과 /static/** 경로를 시큐리티 필터에서 제외
        return webSecurity -> webSecurity.ignoring()
                .requestMatchers(toH2Console()) // /h2-console
                .requestMatchers("/static/**");
    }

    // 요청에 대한 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        // 인증 및 인가 처리 설정, 람다 표현식으로 메소드 체이닝
                        custom -> custom.requestMatchers("/login", "/signup", "/user").permitAll() // 로그인, 회원가입, 사용자 경로는 모두 허용
                                .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
                )

                // 폼 기반 로그인 설정
                .formLogin(custom -> custom.loginPage("/login") // 커스텀 로그인 페이지 설정
                        .defaultSuccessUrl("/articles")) // 로그인 성공 시 이동할 페이지 설정
                // .and()

                // 로그아웃 설정
                .logout(custom -> custom.logoutSuccessUrl("/login") // 로그아웃 성공 시 이동할 페이지
                        .invalidateHttpSession(true)) // 로그아웃 시 세션 무효화
                // .and()

                // CSRF 보호 비활성화 (디폴트는 활성화 상태)
                .csrf(custom -> custom.disable()) // CSRF 보호 비활성화
                .build();
    }

    // 패스워드 암호화 설정 (BCryptPasswordEncoder 사용)
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}