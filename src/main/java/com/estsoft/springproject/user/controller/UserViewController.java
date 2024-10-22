package com.estsoft.springproject.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    // 로그인 페이지로 이동하는 GET 요청 처리
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 페이지 반환
    }

    // 회원가입 페이지로 이동하는 GET 요청 처리
    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html 페이지 반환
    }

    // 로그아웃 처리하는 GET 요청
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 현재 인증된 사용자의 로그아웃을 처리
        new SecurityContextLogoutHandler().logout(
                request,
                response,
                SecurityContextHolder.getContext().getAuthentication() // 현재 사용자의 인증 정보 조회
        );
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리디렉션
    }
}