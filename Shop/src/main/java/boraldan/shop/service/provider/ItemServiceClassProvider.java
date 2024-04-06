package boraldan.shop.service.provider;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.bike_relate.BikeWheel;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.shop.entity.item.transport.car_relate.CarWheel;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.service.i_service.ItemUnifiedService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ItemServiceClassProvider {
    private Map<Class<?>, ItemUnifiedService<?, ?>> itemServiceMap;
    private final ItemUnifiedService<Item, ItemUnifiedRepo<Item>> itemService;
    private final ItemUnifiedService<Car, ItemUnifiedRepo<Car>> carItemService;
    private final ItemUnifiedService<Bike, ItemUnifiedRepo<Bike>> bikeItemService;
    private final ItemUnifiedService<CarWheel, ItemUnifiedRepo<CarWheel>> carWheelItemService;
    private final ItemUnifiedService<BikeWheel, ItemUnifiedRepo<BikeWheel>> bikeWheelItemService;

    @PostConstruct
    private void initServiceMap() {
        this.itemServiceMap = new HashMap<>();
        itemServiceMap.put(Item.class, itemService);
        itemServiceMap.put(Car.class, carItemService);
        itemServiceMap.put(Bike.class, bikeItemService);
        itemServiceMap.put(CarWheel.class, carWheelItemService);
        itemServiceMap.put(BikeWheel.class, bikeWheelItemService);
    }

    public <T extends Item, R extends ItemUnifiedRepo<T>> ItemUnifiedService<T, R> getService(Class<?> clazz) {
        return (ItemUnifiedService<T, R>) this.itemServiceMap.get(clazz);
    }


}
