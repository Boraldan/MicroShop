package boraldan.account.service;


import boraldan.account.service.i_service.CouponService;
import boraldan.entitymicro.account.entity.Coupon;
import boraldan.account.repository.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceV1 implements CouponService {

    private final CouponRepo couponRepo;

    public Optional<Coupon> findValidCoupon(String couponName){
       return couponRepo.findFirstByCouponNameAndValid(couponName, true);
    }
}
