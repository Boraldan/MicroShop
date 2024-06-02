package boraldan.front.service;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.toolbox.builder.CartUnitDtoBuilder;
import boraldan.front.service.i_service.CartFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CartFrontServiceV_1 implements CartFrontService {

    public CartDto addToCart(CartDto cartDto, Item item, LotDto lotDto) {
        cartDto.getCartUnitDtoList().stream()
                .filter(unit -> unit.getItem().getId().equals(lotDto.getItemId()))
                .findFirst()
                .ifPresentOrElse(unit ->
                                unit.setUnitQuantity(unit.getUnitQuantity() + lotDto.getLotQuantity()),
                        () -> cartDto.getCartUnitDtoList().add(CartUnitDtoBuilder.creat()
                                .setItem(item)
                                .setQuantity(lotDto.getLotQuantity())
                                .build())
                );
        return cartDto;
    }

    public CartDto deleteFromCart(CartDto cartDto, UUID itemId) {
        cartDto.setCartUnitDtoList(cartDto.getCartUnitDtoList().stream()
                .filter(unit -> !(unit.getItem().getId().equals(itemId)))
                .toList());
        return cartDto;
    }



}
