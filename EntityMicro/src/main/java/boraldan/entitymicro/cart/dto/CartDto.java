package boraldan.entitymicro.cart.dto;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class CartDto {

    private Customer customer;
    private Coupon coupon;
    private List<CartUnitDto> cartUnitDtoList;

    public CartDto() {
        this.cartUnitDtoList = new ArrayList<>();
    }

    public BigDecimal subTotalPrice() {
        BigDecimal subTotal = new BigDecimal(0);
        if (cartUnitDtoList != null) {
            for (CartUnitDto unit : cartUnitDtoList) {
                for (int i = 1; i <= unit.getUnitQuantity(); i++) {
                    subTotal = subTotal.add(unit.getItem().getPrice().getCustomPrice());
                }
            }
        }
        return subTotal;
    }

    public BigDecimal totalPrice() {
        if (coupon != null) {
            return subTotalPrice().subtract(subTotalPrice().multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
        }
        return subTotalPrice();
    }

    public int lotInCart() {
        return cartUnitDtoList != null ? cartUnitDtoList.stream()
                .filter(Objects::nonNull)
                .mapToInt(CartUnitDto::getUnitQuantity)
                .sum() : 0;
    }

}
