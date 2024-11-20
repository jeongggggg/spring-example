package com.estsoft.springproject.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// TDD
// 1. 계좌 생성 (v)
// 2. 잔금 조회 (v)
// 3. 입/출금 (v)
public class AccountTest {
    Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(10000);
    }

    @Test
    public void testAccount() {
        assertThat(account.getBalance(), is(10000));    // hamcrest로 검증

        account = new Account(200000);
        assertThat(account.getBalance(), is(200000));

        account = new Account(500000);
        assertThat(account.getBalance(), is(500000));
    }

    @Test
    public void testDeposit() {
        account.deposit(100000);
        assertThat(account.getBalance(), is(110000));       // 입금 기능 검증 완료!
    }

    @Test
    public void testWithdraw() {
        account.withdraw(10000);
        assertThat(account.getBalance(), is(0));
    }
}
