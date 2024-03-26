package boraldan.entitymicro.cart.dto;

import boraldan.entitymicro.cart.entity.Cart;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartsDTO {

    List<Cart> carts;
}
