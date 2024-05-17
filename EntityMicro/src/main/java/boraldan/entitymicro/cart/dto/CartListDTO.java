package boraldan.entitymicro.cart.dto;

import boraldan.entitymicro.cart.entity.Cart;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartListDTO {

    List<Cart> carts;
}
