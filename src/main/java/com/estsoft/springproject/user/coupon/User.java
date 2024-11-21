package com.estsoft.springproject.user.coupon;

import java.util.ArrayList;
import java.util.List;

// Production Code
// UserCouponTest 테스트코드에서 사용하는 User 클래스
public class User {
    private List<ICoupon> coupons;

    public User() {
        coupons = new ArrayList<>();
    }

    public int getTotalCouponCount() {
        return coupons.size();
    }

    public void addCoupon(ICoupon coupon) {
        if(coupon.isValid()) {
            coupons.add(coupon);
        }
    }
}
