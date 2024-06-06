package boraldan.front.service;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.shop.dto.LotDto;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.storage.dto.ReserveDtoList;
import boraldan.entitymicro.storage.dto.ReserveDto;
import boraldan.entitymicro.toolbox.builder.CartUnitDtoBuilder;
import boraldan.entitymicro.toolbox.builder.ReserveDtoBuilder;
import boraldan.entitymicro.toolbox.builder.ReserveDtoListBuilder;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.StorageRestClient;
import boraldan.front.service.i_service.CartFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@RequiredArgsConstructor
@Service
public class CartFrontServiceV_1 implements CartFrontService {

    private final RedisService redisService;
    private final StorageRestClient storageRestClient;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Async
    public void setReserveWithInterrupt(CartDto cartDto) {
        // Логика, выполняемая при старте
        int timerCancelReserve = 60;
        String cartReserveKey = String.format("%s_reserve", cartDto.getCustomer().getUsername().toLowerCase());
        redisService.setCart(cartReserveKey, cartDto);
        redisService.deleteCart(cartDto.getCustomer().getUsername().toLowerCase());
        storageRestClient.setReserve(convertListReserveDto(cartDto));

        System.out.printf("Логика, выполняемая при старте задачи. Ключ резерва -- > %s ", cartReserveKey);

        ScheduledFuture<?> scheduledFuture = scheduler.schedule(() -> {
            try {
                // Логика, выполняемая с установленной задержкой. Отмена зарезервированного товара.
                System.out.printf("Логика, выполняемая через %d задачи %s%n", timerCancelReserve, cartReserveKey);

                storageRestClient.deleteReserve(convertListReserveDto(cartDto));

                CartDto backReserveCartDto = redisService.getCart(cartReserveKey);
                redisService.setCart(backReserveCartDto.getCustomer().getUsername().toLowerCase(), cartDto);
                redisService.deleteCart(cartReserveKey);

            } finally {
                scheduledTasks.remove(cartReserveKey);  // Удаляет задачу из карты после завершения
                System.out.println("Задача " + cartReserveKey + " завершена и удалена из списка.");
            }
        }, timerCancelReserve, TimeUnit.SECONDS);

        scheduledTasks.put(cartReserveKey, scheduledFuture);
    }

    public void interruptReserveTimer(CartDto cartDto) {
        String cartReserveKey = String.format("%s_reserve", cartDto.getCustomer().getUsername().toLowerCase());

        ScheduledFuture<?> scheduledFuture = scheduledTasks.get(cartReserveKey);
        if (scheduledFuture != null) {
            storageRestClient.deleteReserve(convertListReserveDto(cartDto));

            CartDto backReserveCartDto = redisService.getCart(cartReserveKey);
            redisService.setCart(backReserveCartDto.getCustomer().getUsername().toLowerCase(), cartDto);
            redisService.deleteCart(cartReserveKey);

            scheduledFuture.cancel(true);  // Отменяет задачу
            scheduledTasks.remove(cartReserveKey);  // Удаляет задачу из ConcurrentHashMap
            System.out.println("Задача " + cartReserveKey + " была прервана.");
        } else {
            System.out.println("interruptReserveTimer --> " + "Такой задачи не существует.");
        }
    }


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


    private ReserveDtoList convertListReserveDto(CartDto cartDto) {
        List<ReserveDto> reserveDtoList = cartDto.getCartUnitDtoList().stream()
                .map(unit -> ReserveDtoBuilder.creat()
                        .setItemId(unit.getItem().getId())
                        .setReserve(unit.getUnitQuantity())
                        .build())
                .toList();
        return ReserveDtoListBuilder.creat().setReserveDtoList(reserveDtoList).build();
    }

}
