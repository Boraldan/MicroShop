package boraldan.front.service;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.toolbox.builder.CartUnitDtoBuilder;
import boraldan.front.rest_client.ShopRestClient;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemFrontService {

    private final ShopRestClient restClient;
    private final ModelMapper modelMapper;

    public CartDto deleteFromCart(CartDto cartDto, UUID itemId) {
        cartDto.setCartUnitDtoList(cartDto.getCartUnitDtoList().stream()
                .filter(unit -> !(unit.getItem().getId().equals(itemId)))
                .toList());
        return cartDto;
    }

    public CartDto addToCart(CartDto cartDto, Item item, LotDto lotDto) {
        Optional<CartUnitDto> cartUnitDto = cartDto.getCartUnitDtoList().stream()
                .filter(unit -> unit.getItem().getId().equals(lotDto.getItemId()))
                .findFirst();

        if (cartUnitDto.isPresent()) {
            cartUnitDto.get().setQuantity(cartUnitDto.get().getQuantity() + lotDto.getQuantity());
        } else {
            cartDto.getCartUnitDtoList().add(CartUnitDtoBuilder.creat()
                    .setItem(item)
                    .setQuantity(lotDto.getQuantity())
                    .build());
        }
        return cartDto;
    }

    public Item getAndConvertItem(UUID itemId, String itemClassName) {
        Class<? extends Item> clazz = null;
        try {
            clazz = (Class<? extends Item>) Class.forName(itemClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restClient.getItem(itemId, clazz);
    }

    public <T extends Item> List<String> getFieldNames(T obj, Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                fieldNames.add(field.getName() + ": " + value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldNames;
    }

    public <T extends Item> T convertToNeedItem(Object item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(item, targetType);
    }


}
