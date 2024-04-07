package boraldan.storage.service;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.entitymicro.storage.entity.transport.car_relate.wheel.CarWheelStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class StorageServiceProviderByCategory {

//    @Getter
    private Map<CategoryName, StorageService<?, ?>> mapBeanStorage;
    private final StorageService<Storage, GlobalJpaRepository<Storage>> storageService;
    private final StorageService<CarStorage, GlobalJpaRepository<CarStorage>> carStorageService;
    private final StorageService<CarWheelStorage, GlobalJpaRepository<CarWheelStorage>> carWheelStorageService;
    private final StorageService<BikeStorage, GlobalJpaRepository<BikeStorage>> bikeStorageService;
    private final StorageService<BikeWheelStorage, GlobalJpaRepository<BikeWheelStorage>> bikeWheelStorageService;

    @PostConstruct
    private void initBeanMap() {
        this.mapBeanStorage = new HashMap<>();
        mapBeanStorage.put(CategoryName.ITEM, storageService);
        mapBeanStorage.put(CategoryName.CAR, carStorageService);
        mapBeanStorage.put(CategoryName.CAR_WHEEL, carWheelStorageService);
        mapBeanStorage.put(CategoryName.BIKE, bikeStorageService);
        mapBeanStorage.put(CategoryName.BIKE_WHEEL, bikeWheelStorageService);
    }

    public <T extends Storage, R extends GlobalJpaRepository<T>> StorageService<T, R> getService(CategoryName categoryName) {
        return (StorageService<T, R>) this.mapBeanStorage.get(categoryName);
    }
}
