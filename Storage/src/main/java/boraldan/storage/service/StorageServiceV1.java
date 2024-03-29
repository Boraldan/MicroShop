package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.StorageJpaRepository;
import boraldan.storage.repository.StorageRepo;
import boraldan.storage.service.i_service.StorageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StorageServiceV1 implements StorageService<Storage, StorageJpaRepository<Storage>> {

    private final StorageRepo storageRepo;
    private final ModelMapper modelMapper;

    @Override
    public StorageJpaRepository<Storage> getStorageRepo() {
        return storageRepo;
    }

    @Override
    public Storage getByItemId(Long itemId) {
        return StorageService.super.getByItemId(itemId);
    }

    public List<Storage> getAll(){
        return storageRepo.findAll();
    }

    @Override
    public Storage saveNew(StorageDto storageDto) {
        return StorageService.super.saveNew(storageDto);
    }

    @Override
    public Storage convertToStorageItem(StorageDto storageDto) {
        return modelMapper.map(storageDto, Storage.class);
    }
}
