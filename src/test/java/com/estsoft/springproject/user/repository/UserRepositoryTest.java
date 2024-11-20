package com.estsoft.springproject.user.repository;

import com.estsoft.springproject.user.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// 실제 데이터 저장해서 검증
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 사용자 이메일로 조회 기능 검증 테스트
    @Test
    public void testFindByEmail() {
        // given : (when절에서 조회하려는) 사용자 정보 저장
        Users user = getUser();
        userRepository.save(user);

        // when
        Users returnUser = userRepository.findByEmail(user.getEmail()).orElseThrow();

        // then
        assertThat(returnUser.getEmail(), is(user.getEmail()));
        assertThat(returnUser.getPassword(), is(user.getPassword()));
    }

    // 사용자 정보 저장 기능
    @Test
    public void testSave(){
        // given
        Users user = getUser(); // ctrl + alt + m 으로 extract method

        // when
        Users saved = userRepository.save(user);

        // then
        assertThat(saved.getEmail(), is(user.getEmail()));
        assertThat(saved.getPassword(), is(user.getPassword()));

    }

    // 사용자 전체 조회 기능
    @Test
    public void testFindAll(){
        // given
        userRepository.save(getUser());
        userRepository.save(getUser());
        userRepository.save(getUser());

        // when : findAll()
        List<Users> list = userRepository.findAll();

        // then : given절에서 저장한 사용자 개수와 when절에서 실제로 호출한 list와 갯수가 같은지 검증
        assertThat(list.size(), is(3));

    }

    private static Users getUser() {
        String email = "test@test.com";
        String password = "pw1234567";
        return new Users(email, password);
    }
}
