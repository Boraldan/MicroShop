package boraldan.storage.service;

import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.storage.repository.GlobalJpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface StorageService<T extends Storage, R extends GlobalJpaRepository<T>> {

    R getStorageRepo();

    default Optional<T> getById(UUID id) {
        return getStorageRepo().findById(id);
    }

    default Optional<T> getByItemId(UUID itemId) {
        return getStorageRepo().findByItemId(itemId);
    }

    default ListStorageDto getByList(ListStorageDto listStorageDto) {
        List<UUID> itemIdList = listStorageDto.getItemIdList().stream().filter(Objects::nonNull).toList();
        List<T> storageList = getStorageRepo().findAllByItemIdIn(itemIdList);
        return new ListStorageDtoBuilder().setStorageList(storageList).build();
    }

    default T save(Storage storage) {
        return getStorageRepo().save(convertToStorageItem(storage, storage.getStorageClazz()));
    }

    default void delete(Storage storage) {
        getStorageRepo().delete(convertToStorageItem(storage, storage.getStorageClazz()));
    }

    T convertToStorageItem(Storage storage, Class<?> clazz);


//
//    R getStorageRepo();
//
//    default T getById(UUID id) {
//        return getStorageRepo().findById(id).orElse(null);
//    }
//
//    default ListStorageDto getByList(ListStorageDto listStorageDto){
//        List<UUID> uuidList =listStorageDto.getUuidList().stream().filter(Objects::nonNull).toList();
//        List<T> storageList =  getStorageRepo().findAllById(uuidList);
//        return new ListStorageDtoBuilder().setStorageList(storageList).build();
//    }
//
//    default T save(StorageDto storageDto) {
//        return getStorageRepo().save(convertToStorageItem(storageDto, storageDto.getStorageClazz()));
//    }
//
//    default void delete(StorageDto storageDto) {
//         getStorageRepo().delete(convertToStorageItem(storageDto, storageDto.getStorageClazz()));
//    }
//
//     T convertToStorageItem(StorageDto storageDto, Class<?> clazz);


}
