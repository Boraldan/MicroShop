package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.cart.dto.CartUnitDto;
import boraldan.entitymicro.shop.entity.item.Item;

public class CartUnitDtoBuilder {

    private final CartUnitDto cartUnitDto;

    private CartUnitDtoBuilder() {
        this.cartUnitDto = new CartUnitDto();
    }

    public static CartUnitDtoBuilder creat() {
        return new CartUnitDtoBuilder();
    }

    public CartUnitDtoBuilder setItem(Item item) {
        this.cartUnitDto.setItem(item);
        return this;
    }

    public CartUnitDtoBuilder setQuantity(Integer quantity) {
        this.cartUnitDto.setQuantity(quantity);
        return this;
    }

    public CartUnitDto build() {
        return this.cartUnitDto;
    }
}
