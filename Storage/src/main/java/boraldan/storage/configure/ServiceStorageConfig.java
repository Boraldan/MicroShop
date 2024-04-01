package boraldan.storage.configure;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import boraldan.storage.service.GlobalStorageService;
import boraldan.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ServiceStorageConfig {

    private final ModelMapper modelMapper;

    @Bean
    public StorageService<Storage, GlobalJpaRepository<Storage>> globalStorageService(
            GlobalJpaRepository<Storage> storageRepo) {
        return new GlobalStorageService<>(storageRepo, modelMapper);
    }

    @Bean
    public StorageService<CarStorage, GlobalJpaRepository<CarStorage>> carStorageService(
            GlobalJpaRepository<CarStorage> storageRepo) {
        return new GlobalStorageService<>(storageRepo, modelMapper);
    }


}
