package com.estsoft.springproject.user.controller;

import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST 요청을 받아 회원가입 처리 후 /login으로 리디렉션
    @PostMapping("/user")
    public String save(@ModelAttribute AddUserRequest addUserRequest) {
        userService.save(addUserRequest); // 회원가입 처리
        return "redirect:/login"; // 로그인 페이지로 리디렉션
    }
}