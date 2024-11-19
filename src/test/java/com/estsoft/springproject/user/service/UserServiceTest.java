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
         // given
         String email = "mock_email";
         String rawPassword = "mock_password";
         AddUserRequest request = new AddUserRequest();
         request.setEmail(email);
         request.setPassword(encoder.encode(rawPassword));

         Mockito.when(userRepository.save(any()))
                 .thenReturn(new Users(request.getEmail(), request.getPassword()));

         // when
         Users returnUser = userService.save(request);


     }
}