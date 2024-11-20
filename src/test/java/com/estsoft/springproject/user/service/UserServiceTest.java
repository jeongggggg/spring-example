package com.estsoft.springproject.user.service;

import com.estsoft.springproject.user.domain.Users;
import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Spy
    BCryptPasswordEncoder encoder;

     @Test
    public void testSave(){
         // given : 호출할 때 필요한 기본 정보들 세팅
         String email = "test@test.com";
         String password = encoder.encode("test_password");
         AddUserRequest user =  AddUserRequest.builder()
                                                 .email(email)
                                                 .password(password)
                                                 .build();

         // userRepository.save -> stub
         Mockito.doReturn(new Users(email, password))
                 .when(userRepository).save(any(Users.class));

         // when : 회원 가입 기능 - 사용자 정보 저장
         Users returnSaved = userService.save(user);

         // then
         assertThat(returnSaved.getEmail(), is(email));
         // 횟수 검증 각각 객체가 몇번 호출 되었는지
         verify(userRepository, times(1)).save(any());
         verify(encoder, times(2)).encode(any());
     }
}