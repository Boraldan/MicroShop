package boraldan.entitymicro.cart.dto;

import boraldan.entitymicro.shop.entity.item.Item;
import lombok.Data;

@Data
public class CartUnitDto {

    private Item item;
    private Integer quantity;

}
