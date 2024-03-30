package boraldan.storage.service.i_service;

import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.StorageJpaRepository;

import java.util.List;
import java.util.UUID;

public interface StorageService<T, R extends StorageJpaRepository<T>> {
    R getStorageRepo();

    default T getById(UUID id) {
        return getStorageRepo().findById(id).orElse(null);
    }

    default T saveNew(StorageDto storageDto) {
        return getStorageRepo().save(convertToStorageItem(storageDto, storageDto.getClazz()));
    }

    default List<T> getAll() {
        return getStorageRepo().findAll();
    }

     T convertToStorageItem(StorageDto storageDto, Class<?> clazz);

}
