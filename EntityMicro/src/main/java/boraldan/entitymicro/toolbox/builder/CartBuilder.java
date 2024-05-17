package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.cart.entity.CartUnit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CartBuilder {

    private final Cart cart;

    private CartBuilder() {
        this.cart = new Cart();
        this.cart.setCreatAt(LocalDateTime.now());
    }

    public static CartBuilder creat() {
        return new CartBuilder();
    }

    public CartBuilder setCustomerId(UUID customerId) {
        this.cart.setCustomerId(customerId);
        return this;
    }

    public CartBuilder setCouponName(String couponName) {
        if (couponName == null) return this;
        this.cart.setCouponName(couponName);
        return this;
    }

    public CartBuilder setCartUnitList(List<CartUnit> cartUnitList) {
        if (cartUnitList == null) return this;
        this.cart.setCartUnitList(cartUnitList);
        return this;
    }

    public Cart build() {
        return this.cart;
    }

}
