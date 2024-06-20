package boraldan.storage.service;

import boraldan.entitymicro.storage.dto.ReserveDtoList;
import boraldan.entitymicro.storage.dto.StorageListDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.storage.repository.GlobalJpaRepository;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UniversalStorageService<T extends Storage> implements StorageService<T, GlobalJpaRepository<T>> {

    private final GlobalJpaRepository<T> storageRepo;
    private final ModelMapper modelMapper;

    @Override
    public GlobalJpaRepository<T> getStorageRepo() {
        return storageRepo;
    }

    @Override
    public Optional<T> getById(UUID storageId) {
        return StorageService.super.getById(storageId);
    }

    @Override
    public Optional<T> getByItemId(UUID itemId) {
        return StorageService.super.getByItemId(itemId);
    }

    @Override
    public StorageListDto getByList(StorageListDto listItemDto) {
        return StorageService.super.getByList(listItemDto);
    }

    @Override
    @Transactional
    public T save(Storage storage) {
        return StorageService.super.save(storage);
    }

    @Override
    @Transactional
    public void deleteByItemId(UUID itemId) {
        StorageService.super.deleteByItemId(itemId);
    }

    @Transactional
    @Override
    public void setReserve(ReserveDtoList reserveDtoList) {
        StorageService.super.setReserve(reserveDtoList);
    }

    @Transactional
    @Override
    public void deleteReserve(ReserveDtoList reserveDtoList) {
        StorageService.super.deleteReserve(reserveDtoList);
    }

    @Transactional
    @Override
    public void deleteReserveAfterSale(ReserveDtoList reserveDtoList) {
        StorageService.super.deleteReserveAfterSale(reserveDtoList);
    }

    public T convertToStorageItem(Storage storage, Class<? extends Storage> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(storage, targetType);
    }
}
