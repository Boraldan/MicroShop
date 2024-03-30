package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.StorageJpaRepository;
import boraldan.storage.service.i_service.StorageService;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class GlobalStorageService<T> implements StorageService<T, StorageJpaRepository<T>> {

    private final StorageJpaRepository<T> storageRepo;
    private final ModelMapper modelMapper;

    @Override
    public StorageJpaRepository<T> getStorageRepo() {
        return storageRepo;
    }

    @Override
    public T getById(UUID id) {
        return StorageService.super.getById(id);
    }

    public List<T> getAll() {
        return storageRepo.findAll();
    }

    @Override
    @Transactional
    public T saveNew(StorageDto storageDto) {
        return StorageService.super.saveNew(storageDto);
    }

    public T convertToStorageItem(StorageDto storageDto, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(storageDto, targetType);
    }
}
