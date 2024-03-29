package boraldan.storage.service.i_service;

import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.StorageJpaRepository;

import java.util.List;

public interface StorageService<T, R extends StorageJpaRepository<T>> {

//    T getByItemId(Long itemId);
//
//    T saveNew (StorageDto storageDto);
//

    R getStorageRepo();

    default T getByItemId(Long itemId) {
        return getStorageRepo().findByItemId(itemId).orElse(null);
    }

    default T saveNew(StorageDto storageDto) {
        if (storageDto.getItemId() == null) {
            return getStorageRepo().save(convertToStorageItem(storageDto));
        }
        //TODO  подумать, что делать, если пришел объект с id, который уже есть в базе
        return convertToStorageItem(storageDto);
    }

    default List<T> getAll() {
        return getStorageRepo().findAll();
    }

    T convertToStorageItem(StorageDto storageDto);
    // return getModelMapper().map(storageDto, T.class);

}
