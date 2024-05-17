package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;

import java.util.List;

public class CartDtoBuilder {
    private final CartDto cartDto;

    private CartDtoBuilder() {
        this.cartDto = new CartDto();
    }

    public static CartDtoBuilder creat() {
        return new CartDtoBuilder();
    }

    public CartDtoBuilder setCustomer(Customer customer) {
        this.cartDto.setCustomer(customer);
        return this;
    }

    public CartDtoBuilder setCoupon(Coupon coupon) {
        if (coupon == null) return this;
        this.cartDto.setCoupon(coupon);
        return this;
    }

    public CartDtoBuilder setCartUnitDtoList(List<CartUnitDto> cartUnitDtoList) {
        if (cartUnitDtoList == null) return this;
        this.cartDto.setCartUnitDtoList(cartUnitDtoList);
        return this;
    }

    public CartDto build() {
        return this.cartDto;
    }
}
