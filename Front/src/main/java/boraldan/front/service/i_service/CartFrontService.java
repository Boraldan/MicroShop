package boraldan.front.service.i_service;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;

import java.util.UUID;

public interface CartFrontService {
    CartDto deleteFromCart(CartDto cartDto, UUID itemId);

    CartDto addToCart(CartDto cartDto, Item item, LotDto lotDto);

}
