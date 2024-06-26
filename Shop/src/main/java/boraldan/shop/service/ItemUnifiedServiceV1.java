package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.storage.dto.StorageListDto;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.toolbox.builder.ListStorageDtoBuilder;
import boraldan.entitymicro.specification.SpecItem;
import boraldan.entitymicro.toolbox.builder.StorageBuilder;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.service.i_service.CategoryService;
import boraldan.shop.service.i_service.ItemUnifiedService;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemUnifiedServiceV1<T extends Item> implements ItemUnifiedService<T, ItemUnifiedRepo<T>> {

    private final Class<T> clazz;
    private final ItemUnifiedRepo<T> itemRepo;
    private final CategoryService categoryService;
    private final StorageFeign storageFeign;
    private T t;
    private Class<? extends Storage> storageClazz;

    @Override
    public ItemUnifiedRepo<T> getItemRepo() {
        return itemRepo;
    }

//    @Override
//    public Optional<T> getById(UUID id) {
//        Optional<T> item = ItemUnifiedService.super.getById(id);
//        item.ifPresent(value -> value.setStorage(storageFeign.getQuantity(StorageBuilder.creat(Storage.class).setItemId(value.getId())
//                .setStorageClazz(value.getStorageClazz()).build()).getBody()));
//        return item;
//    }

    @Override
    public Optional<T> getById(UUID itemId) {
        Optional<T> item = ItemUnifiedService.super.getById(itemId);
        item.ifPresent(value -> value.setStorage(storageFeign.getQuantity(itemId).getBody()));
        return item;
    }


    @Override
    public List<T> getAll() {
        initT();
        List<T> originalList = ItemUnifiedService.super.getAll();
        if (originalList.isEmpty()) return originalList;
        StorageListDto storageListDto = this.getListStorageDto(originalList);
        if (storageListDto == null) return originalList;
        return addStorageToListT(originalList, storageListDto.getStorageList());
    }

    @Override
    public Page<T> getAllBySpecification(SpecItem specItem, Pageable pageable) {
        Page<T> originalPage = ItemUnifiedService.super.getAllBySpecification(specItem, pageable);
        initT();
        List<T> originalList = originalPage.getContent();
        if (originalList.isEmpty()) return originalPage;
        StorageListDto storageListDto = this.getListStorageDto(originalList);
        if (storageListDto == null) return originalPage;
        originalList = addStorageToListT(originalList, storageListDto.getStorageList());
        return new PageImpl<>(originalList, originalPage.getPageable(), originalPage.getTotalElements());
    }

//    @Override
//    public List<T> getByUuidList(List<UUID> uuidList) {
//        return ItemUnifiedService.super.getByUuidList(uuidList);
//    }

    @Override
    public List<T> getByUuidList(List<UUID> uuidList) {
        List<T> originalList = ItemUnifiedService.super.getByUuidList(uuidList);
        if (originalList.isEmpty()) return originalList;
        StorageListDto storageListDto = this.getListStorageDto(originalList);
        return addStorageToListT(originalList, storageListDto != null ? storageListDto.getStorageList() : List.of());

    }

    @Override
    @Transactional
    public <E extends Item> T save(E item) {
        item.setCategory(categoryService.getCategoryByCategoryName(item.getCategory().getCategoryName()).get());
        return ItemUnifiedService.super.save(item);
    }

    @Override
    @Transactional
    public void deleteById(UUID itemId) {
        ItemUnifiedService.super.deleteById(itemId);
        storageFeign.deleteByItem(itemId);
    }

//    @Override
//    @Transactional
//    public <E extends Item> void delete(E item) {
//        ItemUnifiedService.super.delete(item);
//    }

    public <E extends Item> T convertToT(E item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return new ModelMapper().map(item, targetType);
    }

    private StorageListDto getListStorageDto(List<T> originalList) {
        List<UUID> itemIdList = originalList.stream().map(T::getId).toList();
        return storageFeign.getByList(ListStorageDtoBuilder.creat()
                        .setStorageClazz(storageClazz)
                        .setItemIdList(itemIdList)
                        .build())
                .getBody();
    }

    private List<T> addStorageToListT(List<T> itemList, List<Storage> storageList) {
        Map<UUID, Storage> storageMap = new HashMap<>();
        for (Storage storage : storageList) {
            storageMap.put(storage.getItemId(), storage);
        }
        return itemList.stream().map(item -> {
            item.setStorage(storageMap.get(item.getId()) != null ? storageMap.get(item.getId())
                    : StorageBuilder.creat(Storage.class)
                    .setItemId(item.getId())
                    .setQuantity(0)
                    .setReserve(0)
                    .build());
            return item;
        }).toList();
    }

    private void initT() {
        this.t = createInstance(clazz);
        setStorageClazz();
    }

    private T createInstance(Class<T> clazz) {
        try {
            if (clazz.equals(Item.class)) return null;
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setStorageClazz() {
        if (clazz.equals(Item.class)) {
            this.storageClazz = Storage.class;
        } else {
            this.storageClazz = t.getStorageClazz();
        }
    }


}
