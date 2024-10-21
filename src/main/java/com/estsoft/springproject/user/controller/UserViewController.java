package com.estsoft.springproject.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    // GET /login -> 로그인 페이지로 연결
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 페이지 반환
    }

    // GET /signup -> 회원가입 페이지로 연결
    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html 페이지 반환
    }
}