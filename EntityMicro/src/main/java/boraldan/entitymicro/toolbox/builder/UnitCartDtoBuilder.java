package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.cart.dto.CartUnitDto;
import boraldan.entitymicro.shop.entity.item.Item;

public class UnitCartDtoBuilder {
    private final CartUnitDto cartUnitDto;

    private UnitCartDtoBuilder() {
        this.cartUnitDto = new CartUnitDto();
    }

    public static UnitCartDtoBuilder creat() {
        return new UnitCartDtoBuilder();
    }

    public UnitCartDtoBuilder setItem(Item item) {
        if (item == null) return this;
        this.cartUnitDto.setItem(item);
        return this;
    }

    public UnitCartDtoBuilder setQuantity(Integer quantity) {
        if (quantity == null) return this;
        this.cartUnitDto.setQuantity(quantity);
        return this;
    }

    public CartUnitDto build() {
        return cartUnitDto;
    }

}
