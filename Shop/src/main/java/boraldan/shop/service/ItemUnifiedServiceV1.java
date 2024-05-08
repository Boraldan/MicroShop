package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.dto.StorageBuilder;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.service.i_service.CategoryService;
import boraldan.shop.service.i_service.ItemUnifiedService;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemUnifiedServiceV1<T extends Item> implements ItemUnifiedService<T, ItemUnifiedRepo<T>> {

    private final Class<T> clazz;
    private final ItemUnifiedRepo<T> itemRepo;
    private final CategoryService categoryService;
    private final StorageFeign storageFeign;
    private T t;
    private Class<?> storageClazz;

    @Override
    public ItemUnifiedRepo<T> getItemRepo() {
        return itemRepo;
    }

    @Override
    public Optional<T> getById(UUID id) {
        Optional<T> item = ItemUnifiedService.super.getById(id);
        item.ifPresent(value -> value.setStorage(storageFeign.getQuantity(new StorageBuilder().setItemId(value.getId())
                .setStorageClazz(value.getStorageClazz()).build()).getBody()));
        return item;
    }

    @Override
    public List<T> getAll() {
        initT();
        List<T> originalList = ItemUnifiedService.super.getAll();
        if (originalList.isEmpty()) return originalList;

        List<UUID> itemIdList = originalList.stream().map(T::getId).toList();
        ListStorageDto listStorageDto = storageFeign.getByList(new ListStorageDtoBuilder()
                .setStorageClazz(storageClazz).setItemIdList(itemIdList).build()).getBody();

        if (listStorageDto == null) return originalList;
        return addStorageToListT(originalList, listStorageDto);
    }

    @Override
    public Page<T> getAllBySpecification(BigDecimal minScore, BigDecimal maxScore, String partName, Integer page) {
//        return ItemUnifiedService.super.getAllBySpecification(minScore, maxScore, partName, page);

        Page<T> originalPage = ItemUnifiedService.super.getAllBySpecification(minScore, maxScore, partName, page);

        initT();
        List<T> originalList = originalPage.getContent();

        if (originalList.isEmpty()) return originalPage;

        List<UUID> itemIdList = originalList.stream().map(T::getId).toList();
        ListStorageDto listStorageDto = storageFeign.getByList(new ListStorageDtoBuilder()
                .setStorageClazz(storageClazz).setItemIdList(itemIdList).build()).getBody();

        if (listStorageDto == null) return originalPage;

        originalList = addStorageToListT(originalList, listStorageDto);

        return new PageImpl<>(originalList, originalPage.getPageable(), originalPage.getTotalElements());
    }


    @Override
    @Transactional
    public <E extends Item> T save(E item) {
        item.setCategory(categoryService.getCategoryByCategoryName(item.getCategory().getCategoryName()).get());
        return ItemUnifiedService.super.save(item);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        ItemUnifiedService.super.deleteById(id);
    }

    @Override
    @Transactional
    public <E extends Item> void delete(E item) {
        ItemUnifiedService.super.delete(item);
    }

    public <E extends Item> T convertToT(E item, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return new ModelMapper().map(item, targetType);
    }

    private List<T> addStorageToListT(List<T> itemList, ListStorageDto listStorageDto) {
        Map<UUID, Storage> storageMap = new HashMap<>();
        for (Storage storage : listStorageDto.getStorageList()) {
            storageMap.put(storage.getItemId(), storage);
        }
        return itemList.stream().map(item -> {
            item.setStorage(storageMap.get(item.getId()));
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
