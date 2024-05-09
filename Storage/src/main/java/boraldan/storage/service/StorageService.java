package boraldan.storage.service;

import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.toolbox.builder.ListStorageDtoBuilder;
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
        return ListStorageDtoBuilder.creat().setStorageList(storageList).build();
    }

    default T save(Storage storage) {
        return getStorageRepo().save(convertToStorageItem(storage, storage.getStorageClazz()));
    }

    default void deleteByItemId(UUID itemId){
        getStorageRepo().deleteByItemId(itemId);
    }

    T convertToStorageItem(Storage storage, Class<? extends Storage> clazz);

}
