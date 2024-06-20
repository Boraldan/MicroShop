package boraldan.storage.service;

import boraldan.entitymicro.storage.dto.ReserveDto;
import boraldan.entitymicro.storage.dto.ReserveDtoList;
import boraldan.entitymicro.storage.dto.StorageListDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.toolbox.builder.ListStorageDtoBuilder;
import boraldan.storage.repository.GlobalJpaRepository;

import java.util.*;
import java.util.stream.Collectors;

public interface StorageService<T extends Storage, R extends GlobalJpaRepository<T>> {

    R getStorageRepo();

    default Optional<T> getById(UUID id) {
        return getStorageRepo().findById(id);
    }

    default Optional<T> getByItemId(UUID itemId) {
        return getStorageRepo().findByItemId(itemId);
    }

    default StorageListDto getByList(StorageListDto storageListDto) {
        List<UUID> itemIdList = storageListDto.getItemIdList().stream().filter(Objects::nonNull).toList();
        List<T> storageList = getStorageRepo().findAllByItemIdIn(itemIdList);
        return ListStorageDtoBuilder.creat().setStorageList(storageList).build();
    }

    default T save(Storage storage) {
        return getStorageRepo().save(convertToStorageItem(storage, storage.getStorageClazz()));
    }

    default void deleteByItemId(UUID itemId) {
        getStorageRepo().deleteByItemId(itemId);
    }

    default void setReserve(ReserveDtoList reserveDtoList) {
        List<UUID> itemIdList = itemIdList(reserveDtoList);
        List<T> storageList = getStorageRepo().findAllByItemIdIn(itemIdList);
        Map<UUID, Storage> storageMap = storageList.stream()
                .collect(Collectors.toMap(Storage::getItemId, storage -> storage));

        reserveDtoList.getReserveDtoList().forEach(reserveDto -> {
            Storage storage = storageMap.get(reserveDto.getItemId());
            if (storage != null) {
                storage.setQuantity(storage.getQuantity() - reserveDto.getCartReserve());
                storage.setReserve(storage.getReserve() + reserveDto.getCartReserve());
            }
        });

        getStorageRepo().saveAll(storageList);
    }

    default void deleteReserve(ReserveDtoList reserveDtoList) {
        List<UUID> itemIdList = itemIdList(reserveDtoList);
        List<T> storageList = getStorageRepo().findAllByItemIdIn(itemIdList);
        Map<UUID, Storage> storageMap = storageList.stream()
                .collect(Collectors.toMap(Storage::getItemId, storage -> storage));

        reserveDtoList.getReserveDtoList().forEach(reserveDto -> {
            Storage storage = storageMap.get(reserveDto.getItemId());
            if (storage != null) {
                storage.setQuantity(storage.getQuantity() + reserveDto.getCartReserve());
                storage.setReserve(storage.getReserve() - reserveDto.getCartReserve());
            }
        });

        getStorageRepo().saveAll(storageList);
    }

    default void deleteReserveAfterSale(ReserveDtoList reserveDtoList) {
        List<UUID> itemIdList = itemIdList(reserveDtoList);
        List<T> storageList = getStorageRepo().findAllByItemIdIn(itemIdList);
        Map<UUID, Storage> storageMap = storageList.stream()
                .collect(Collectors.toMap(Storage::getItemId, storage -> storage));

        reserveDtoList.getReserveDtoList().forEach(reserveDto -> {
            Storage storage = storageMap.get(reserveDto.getItemId());
            if (storage != null) {
                storage.setReserve(storage.getReserve() - reserveDto.getCartReserve());
            }
        });

        getStorageRepo().saveAll(storageList);
    }

    default List<UUID> itemIdList(ReserveDtoList reserveDtoList) {
        return reserveDtoList.getReserveDtoList().stream()
                .filter(Objects::nonNull)
                .map(ReserveDto::getItemId)
                .toList();
    }

    T convertToStorageItem(Storage storage, Class<? extends Storage> clazz);

}
