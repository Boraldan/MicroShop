package boraldan.entitymicro.cart.dto;

import boraldan.entitymicro.shop.entity.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartUnitDto {

    private Item item;
    private Integer unitQuantity;

}
