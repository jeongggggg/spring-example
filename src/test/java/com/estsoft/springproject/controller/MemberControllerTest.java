package com.estsoft.springproject.controller;

import com.estsoft.springproject.entity.Member;
import com.estsoft.springproject.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll();  // 이전 데이터 삭제
    }

    @Test
    public void testGetAllMember() throws Exception {
        // given: 멤버 저장
        Member member1 = new Member();
        member1.setName("user1");  // name을 user1로 설정
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("user2");  // name을 user2로 설정
        memberRepository.save(member2);

        // when: GET 요청
        ResultActions resultActions = mockMvc.perform(get("/members")
                .accept(MediaType.APPLICATION_JSON));

        // then: 응답 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("user1"))  // 예상 값 "user1"
                .andExpect(jsonPath("$[1].name").value("user2"));  // 예상 값 "user2"
    }

    @Test
    void testSaveMember() throws Exception {
        // given: 멤버 정보 생성
        Member member = new Member();
        member.setName("user1");

        // when: POST 요청
        ResultActions resultActions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"user1\"}"));

        // then: 응답 검증
        resultActions.andExpect(status().isCreated())  // 201 상태 코드 확인
                .andExpect(jsonPath("$.name").value("user1"));  // 저장된 member name 확인
    }

}