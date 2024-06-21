package boraldan.account.controller;

import boraldan.account.service.i_service.CouponService;
import boraldan.entitymicro.account.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/coupon")
@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/get")
    ResponseEntity<Coupon> getCoupon(@RequestBody String couponName) {
        System.out.println(couponName != null ? couponName : "имя купона не передано ");
        if (couponName == null) return ResponseEntity.ok(null);
        return ResponseEntity.ok(couponService.findValidCoupon(couponName).orElse(null));
    }

}
