package com.estsoft.springproject.coupon;

import com.estsoft.springproject.user.coupon.DummyCoupon;
import com.estsoft.springproject.user.coupon.ICoupon;
import com.estsoft.springproject.user.coupon.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCouponTest {

    @Test
    public void testAddCoupon(){
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = new DummyCoupon(); // dummy 쿠폰을 만들어서 사용

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }

    @Test
    public void 쿠폰이_유효할_경우에만_유저에게_발급한다(){
        User user = new User("area00");
        assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

        ICoupon coupon = Mockito.mock(ICoupon.class); // mock 객체 - 행위 정의 가능
        Mockito.when(coupon.isValid()).thenReturn(true); //stub
        // Mockito.doReturn(true).when(coupon).isValid(); // 위 코드와 동일한 동작

        user.addCoupon(coupon);
        assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
    }

    @Test
    public void 쿠폰이_유효하지_않을_경우_발급안됨(){
        //
    }
}
