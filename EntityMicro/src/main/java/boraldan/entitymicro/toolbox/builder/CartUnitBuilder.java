package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.cart.entity.CartUnit;

import java.util.UUID;

public class CartUnitBuilder {

    private final CartUnit cartUnit;

    private CartUnitBuilder() {
        this.cartUnit = new CartUnit();
    }

    public static CartUnitBuilder create() {
        return new CartUnitBuilder();
    }

    public CartUnitBuilder setId(UUID id) {
        this.cartUnit.setId(id);
        return this;
    }

    public CartUnitBuilder setCart(Cart cart) {
        this.cartUnit.setCart(cart);
        return this;
    }

    public CartUnitBuilder setItemId(UUID itemId) {
        this.cartUnit.setItemId(itemId);
        return this;
    }

    public CartUnitBuilder setQuantity(Integer quantity) {
        this.cartUnit.setQuantity(quantity);
        return this;
    }

    public CartUnit build() {
        return this.cartUnit;
    }
}
