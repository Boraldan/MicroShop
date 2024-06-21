package boraldan.front.utilit;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.front.rest_client.CouponRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class CouponValidator implements Validator {

    private final CouponRestClient couponRestClient;

    @Override
    public boolean supports(Class<?> clazz) {
        return Coupon.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    public Coupon validateCoupon(Object target, Errors errors) {
        Coupon checkCoupon = (Coupon) target;

        if (checkCoupon == null) {
            errors.rejectValue("couponName", "", "Имя купона не указано");
            return null;
        }

        Coupon validCoupon = couponRestClient.getCoupon(checkCoupon.getCouponName());
        if (validCoupon == null) {
            errors.rejectValue("couponName", "", "Купон не действителен");
        }
        return validCoupon;
    }
}
