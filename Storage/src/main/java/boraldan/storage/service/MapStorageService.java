package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapStorageService {

    @Getter
    Map<Class<?>, StorageService<?, ?>> mapBean;
    private final StorageService<CarStorage, GlobalJpaRepository<CarStorage>> carStorageService;
    private final StorageService<Storage, GlobalJpaRepository<Storage>> storageService;

    @PostConstruct
    public void initBeanMap() {
        this.mapBean = new HashMap<>();
        mapBean.put(CarStorage.class, carStorageService);
        mapBean.put(Storage.class, storageService);
    }
}
