package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.storage.repository.GlobalJpaRepository;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.UUID;


@RequiredArgsConstructor
public class GlobalStorageService<T extends Storage> implements StorageService<T, GlobalJpaRepository<T>> {

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




//
//    public ListItemDto getByList(ListItemDto listItemDto) {
//        List<UUID> uuidList = listItemDto.getItems().stream().map(Item::getStorageId).filter(Objects::nonNull).toList();
//        List<T> storageList = storageRepo.findAllById(uuidList);
//
//        Map<UUID, Storage> storageMap = new HashMap<>();
//        for (Storage entity : storageList) {
//            storageMap.put(entity.getId(), entity);
//        }
//
//        listItemDto.setItems(listItemDto.getItems().stream().map(el -> {
//            el.setStorage(storageMap.get(el.getStorageId()));
//            return el;
//        }).toList());
//
//        return listItemDto;
//
//    }


}
