package boraldan.front.service.i_service;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;

import java.util.UUID;

public interface CartFrontService {
    CartDto addItemToCart(CartDto cartDto, Item item, LotDto lotDto);
    CartDto delItemFromCart(CartDto cartDto, UUID itemId);
    void setReserveWithInterrupt(CartDto cartDto);
    void interruptReserveTimer(CartDto cartDto);
    void interruptReserveTimerByBooked(CartDto cartDto);

}
