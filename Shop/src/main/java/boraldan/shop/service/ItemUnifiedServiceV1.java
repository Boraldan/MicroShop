package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.service.i_service.CategoryService;
import boraldan.shop.service.i_service.ItemUnifiedService;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public T getById(Long id) {
        return ItemUnifiedService.super.getById(id);
    }

    @Override
    public List<T> getAll() {
        initT();
        List<T> list = ItemUnifiedService.super.getAll();
        if (list.isEmpty()) return list;

        List<Long> itemIdList = list.stream().map(T::getId).toList();
        ListStorageDto listStorageDto = storageFeign.getByList(new ListStorageDtoBuilder()
                .setStorageClazz(storageClazz).setItemIdList(itemIdList).build()).getBody();

        if (listStorageDto == null) return list;
        return addStorageToListT(list, listStorageDto);
    }

    @Override
    @Transactional
    public <E extends Item> T save(E item) {
        item.setCategory(categoryService.getCategoryByCategoryName(item.getCategory().getCategoryName()).get());
        return ItemUnifiedService.super.save(item);
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
        Map<Long, Storage> storageMap = new HashMap<>();
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
