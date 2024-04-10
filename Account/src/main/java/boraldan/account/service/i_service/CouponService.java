package boraldan.account.service.i_service;

import boraldan.entitymicro.account.entity.Coupon;

import java.util.Optional;

public interface CouponService {

    Optional<Coupon> findValidCoupon(String couponName);
}
