package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.GlobalJpaRepository;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class GlobalStorageService<T> implements StorageService<T, GlobalJpaRepository<T>> {

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

    public List<T> getAll() {
        return storageRepo.findAll();
    }

    @Override
    @Transactional
    public T save(StorageDto storageDto) {
        return StorageService.super.save(storageDto);
    }

    @Override
    public void delete(StorageDto storageDto) {
        StorageService.super.delete(storageDto);
    }

    public T convertToStorageItem(StorageDto storageDto, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return modelMapper.map(storageDto, targetType);
    }
}
