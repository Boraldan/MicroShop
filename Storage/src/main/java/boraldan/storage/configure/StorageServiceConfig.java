package boraldan.storage.configure;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.entitymicro.storage.entity.transport.car_relate.wheel.CarWheelStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import boraldan.storage.service.UniversalStorageService;
import boraldan.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StorageServiceConfig {

    private final ModelMapper modelMapper;

    @Bean
    public StorageService<Storage, GlobalJpaRepository<Storage>> globalStorageService(
            GlobalJpaRepository<Storage> storageRepo) {
        return new UniversalStorageService<>(storageRepo, modelMapper);
    }

    @Bean
    public StorageService<CarStorage, GlobalJpaRepository<CarStorage>> carStorageService(
            GlobalJpaRepository<CarStorage> storageRepo) {
        return new UniversalStorageService<>(storageRepo, modelMapper);
    }
    @Bean
    public StorageService<CarWheelStorage, GlobalJpaRepository<CarWheelStorage>> carWheelStorageService(
            GlobalJpaRepository<CarWheelStorage> storageRepo) {
        return new UniversalStorageService<>(storageRepo, modelMapper);
    }

    @Bean
    public StorageService<BikeStorage, GlobalJpaRepository<BikeStorage>> bikeStorageService(
            GlobalJpaRepository<BikeStorage> storageRepo) {
        return new UniversalStorageService<>(storageRepo, modelMapper);
    }

    @Bean
    public StorageService<BikeWheelStorage, GlobalJpaRepository<BikeWheelStorage>> bikeWheelStorageService(
            GlobalJpaRepository<BikeWheelStorage> storageRepo) {
        return new UniversalStorageService<>(storageRepo, modelMapper);
    }



}
