package boraldan.storage.configure;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.StorageJpaRepository;
import boraldan.storage.service.GlobalStorageService;
import boraldan.storage.service.i_service.StorageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ServiceBean {

    private final ModelMapper modelMapper;

    @Bean
    public StorageService<Storage, StorageJpaRepository<Storage>> globalStorageService(
            StorageJpaRepository<Storage> storageRepo) {
        return new GlobalStorageService<>(storageRepo, modelMapper);
    }

    @Bean
    public StorageService<CarStorage, StorageJpaRepository<CarStorage>> carStorageService(
            StorageJpaRepository<CarStorage> storageRepo) {
        return new GlobalStorageService<>(storageRepo, modelMapper);
    }

}
