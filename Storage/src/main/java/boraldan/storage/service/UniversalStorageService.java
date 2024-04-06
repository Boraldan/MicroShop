package boraldan.storage.service;

import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.storage.repository.GlobalJpaRepository;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.UUID;


@RequiredArgsConstructor
public class UniversalStorageService<T extends Storage> implements StorageService<T, GlobalJpaRepository<T>> {

    private final GlobalJpaRepository<T> storageRepo;
    private final ModelMapper modelMapper;

    @Override
    public GlobalJpaRepository<T> getStorageRepo() {
        return storageRepo;
    }

    @Override
    public T getById(UUID id) {
        return StorageService.super.getById(id);
    }

    @Override
    public ListStorageDto getByList(ListStorageDto listItemDto) {
        return StorageService.super.getByList(listItemDto);
    }

    @Override
    @Transactional
    public T save(Storage storage) {
        return StorageService.super.save(storage);
    }

    @Override
    public void delete(Storage storage) {
        StorageService.super.delete(storage);
    }


    public T convertToStorageItem(Storage storage, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(storage, targetType);
    }


}
