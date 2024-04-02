package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.GlobalJpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface StorageService<T extends Storage, R extends GlobalJpaRepository<T>> {

    R getStorageRepo();

    default T getById(UUID id) {
        return getStorageRepo().findById(id).orElse(null);
    }

    default ListStorageDto getByList(ListStorageDto listStorageDto){
        List<UUID> uuidList =listStorageDto.getUuidList().stream().filter(Objects::nonNull).toList();
        List<T> storageList =  getStorageRepo().findAllById(uuidList);
        return new ListStorageDtoBuilder().setStorageList(storageList).build();
    }

    default T save(StorageDto storageDto) {
        return getStorageRepo().save(convertToStorageItem(storageDto, storageDto.getStorageClazz()));
    }

    default void delete(StorageDto storageDto) {
         getStorageRepo().delete(convertToStorageItem(storageDto, storageDto.getStorageClazz()));
    }

     T convertToStorageItem(StorageDto storageDto, Class<?> clazz);


}
