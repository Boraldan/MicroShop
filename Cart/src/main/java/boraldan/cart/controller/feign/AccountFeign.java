package boraldan.cart.controller.feign;

import boraldan.entitymicro.account.entity.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Primary
@FeignClient(name = "account", fallback = AccountFeignFallback.class)
public interface AccountFeign {
    @PostMapping("/coupon/get")
    ResponseEntity<Coupon> getCoupon(String couponName);
}

@Component
class AccountFeignFallback implements AccountFeign {

    @Override
    public ResponseEntity<Coupon> getCoupon(String couponName) {
        return null;
    }
}