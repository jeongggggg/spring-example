package com.estsoft.springproject.tdd;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import static org.hamcrest.Matchers.*;

// TDD
// 1. 계좌 생성
// 2. 잔금 조회
// 3. 입/출금
public class AccountTest {
    Account account; // setUp 에서 스코프가 안맞기 때문에 필드로 빼줌

    @BeforeEach
    public void setUp() {
        Account account = new Account(10000);
    }

    @Test
    public void testAccount() {
        // hamcrest로 검증
        Account account = new Account(10000);
        assertThat(account.getBalance(), Matchers.is(10000));

        account = new Account(20000);
        assertThat(account.getBalance(), Matchers.is(20000));

        account = new Account(30000);
        assertThat(account.getBalance(), Matchers.is(30000));
    }

    @Test
    public void testDeposit() {
        Account account = new Account(10000);
        account.deposit(100000);
        assertThat(account.getBalance(), is(110000));
    }

    @Test
    public void testWithdraw() {
        Account account = new Account(10000);
        account.withdraw(10000);
        assertThat(account.getBalance(), is(0));
    }
}
