package com.estsoft.springproject.controller;

import com.estsoft.springproject.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 위해 실제 서버가 뜬 것처럼 하기 위해 추가(테스트 서버)  (when 절을 위함)
@SpringBootTest // @SpeingBootApplication 을 붙인 클래스를 찾아서 테스트와 관련된 bean 들을 넣어준다. (when 절을 위함)
@AutoConfigureMockMvc
class MemberControllerTest {
    // 테스트 관련 세팅(테스르를 위한 관련된 bean 주입) (when 절을 위함)
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @Autowired // given 테스트를 위한 실제 레포지토리 의존성 주입
    MemberRepository memberRepository;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Disabled
    public void testGetAllMember() throws Exception {
        // given : 멤버 목록 저장  (생략)

        // when : GET /members
        ResultActions resultActions = mockMvc.perform(get("/members").accept(MediaType.APPLICATION_JSON)); // accept(MediaType.APPLICATION_JSON) -> 요청에 대한 응답은 JSON 형태로 받겠다.

        // then : response 검증
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(1)) // id 값에 대한 검증, given 절에서 특정 값을 할당해주었다면 value에 할당한 값을 넣어주어도 된다.
                .andExpect(jsonPath("$[1].id").value(2))
        ;
    }
}