package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.dto.LotDto;

import java.util.UUID;

public class LotDtoBuilder {

    private final LotDto lotDto;

    private LotDtoBuilder() {
        this.lotDto = new LotDto();
    }

    public static LotDtoBuilder creat() {
        return new LotDtoBuilder();
    }

    public LotDtoBuilder setQuantity(Integer quantity) {
        if (quantity == null) quantity = 0;
        this.lotDto.setLotQuantity(quantity);
        return this;
    }

    public LotDtoBuilder setItemId(UUID itemId) {
        this.lotDto.setItemId(itemId);
        return this;
    }

    public LotDtoBuilder setItemClassName(String itemClassName) {
        this.lotDto.setItemClassName(itemClassName);
        return this;
    }

    public LotDtoBuilder setLimitQuantity(Integer limitQuantity) {
        this.lotDto.setLimitQuantity(limitQuantity);
        return this;
    }

    public LotDto build() {
        return this.lotDto;
    }
}
