package com.estsoft.springproject.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// 회원가입 요청 시 컨트롤러에서 입력받는 정보
@Getter
@Setter
@Builder
public class AddUserRequest {
    private String email;
    private String password;
}