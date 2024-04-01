package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.GlobalJpaRepository;

import java.util.List;
import java.util.UUID;

public interface StorageService<T, R extends GlobalJpaRepository<T>> {
    R getStorageRepo();

    default T getById(UUID id) {
        return getStorageRepo().findById(id).orElse(null);
    }

    default List<T> getAll() {
        return getStorageRepo().findAll();
    }
    default T save(StorageDto storageDto) {
        return getStorageRepo().save(convertToStorageItem(storageDto, storageDto.getClazz()));
    }

    default void delete(StorageDto storageDto) {
         getStorageRepo().delete(convertToStorageItem(storageDto, storageDto.getClazz()));
    }

     T convertToStorageItem(StorageDto storageDto, Class<?> clazz);

}
