package boraldan.shop.config;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.shop.entity.item.transport.bike_relate.BikeWheel;
import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.shop.entity.item.transport.car_relate.CarWheel;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.service.i_service.CategoryService;
import boraldan.shop.service.i_service.ItemUnifiedService;
import boraldan.shop.service.ItemUnifiedServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemServiceConfig {

    @Bean
    public ItemUnifiedService<Item, ItemUnifiedRepo<Item>> itemService(
            ItemUnifiedRepo<Item> itemJpaRepo, CategoryService categoryService, StorageFeign storageFeign) {
        return new ItemUnifiedServiceV1<>(Item.class, itemJpaRepo, categoryService, storageFeign);
    }

    @Bean
    public ItemUnifiedService<Car, ItemUnifiedRepo<Car>> carItemService(
            ItemUnifiedRepo<Car> itemJpaRepo, CategoryService categoryService, StorageFeign storageFeign) {
        return new ItemUnifiedServiceV1<>(Car.class, itemJpaRepo, categoryService, storageFeign);
    }

    @Bean
    public ItemUnifiedService<Bike, ItemUnifiedRepo<Bike>> bikeItemService(
            ItemUnifiedRepo<Bike> itemJpaRepo, CategoryService categoryService, StorageFeign storageFeign) {
        return new ItemUnifiedServiceV1<>(Bike.class, itemJpaRepo, categoryService, storageFeign);
    }

    @Bean
    public ItemUnifiedService<CarWheel, ItemUnifiedRepo<CarWheel>> carWheelItemService(
            ItemUnifiedRepo<CarWheel> itemJpaRepo, CategoryService categoryService, StorageFeign storageFeign) {
        return new ItemUnifiedServiceV1<>(CarWheel.class, itemJpaRepo, categoryService, storageFeign);
    }

    @Bean
    public ItemUnifiedService<BikeWheel, ItemUnifiedRepo<BikeWheel>> bikeWheelItemService(
            ItemUnifiedRepo<BikeWheel> itemJpaRepo, CategoryService categoryService, StorageFeign storageFeign) {
        return new ItemUnifiedServiceV1<>(BikeWheel.class, itemJpaRepo, categoryService, storageFeign);
    }

}
