package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.entitymicro.storage.entity.transport.car_relate.wheel.CarWheelStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StorageServiceProvider {

    @Getter
    private Map<Class<?>, StorageService<?, ?>> mapBeanStorage;
    private final StorageService<Storage, GlobalJpaRepository<Storage>> storageService;
    private final StorageService<CarStorage, GlobalJpaRepository<CarStorage>> carStorageService;
    private final StorageService<CarWheelStorage, GlobalJpaRepository<CarWheelStorage>> carWheelStorageService;
    private final StorageService<BikeStorage, GlobalJpaRepository<BikeStorage>> bikeStorageService;
    private final StorageService<BikeWheelStorage, GlobalJpaRepository<BikeWheelStorage>> bikeWheelStorageService;

    @PostConstruct
    public void initBeanMap() {
        this.mapBeanStorage = new HashMap<>();
        mapBeanStorage.put(Storage.class, storageService);
        mapBeanStorage.put(CarStorage.class, carStorageService);
        mapBeanStorage.put(CarWheelStorage.class, carWheelStorageService);
        mapBeanStorage.put(BikeStorage.class, bikeStorageService);
        mapBeanStorage.put(BikeWheelStorage.class, bikeWheelStorageService);
    }
}
