package boraldan.order.service;


import boraldan.entitymicro.order.entity.Coupon;
import boraldan.order.repository.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepo couponRepo;

    public Optional<Coupon> findValidCoupon(String name){
       return couponRepo.findFirstByNameAndValid(name, true);
    }
}
