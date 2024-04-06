package boraldan.shop.service.i_service;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.shop.repository.ItemUnifiedRepo;

import java.util.List;

public interface ItemUnifiedService<T extends Item, R extends ItemUnifiedRepo<T>> {

    R getItemRepo();

    default T getById(Long id) {
        return getItemRepo().findById(id).orElse(null);
    }

    default List<T> getAll() {
        return getItemRepo().findAll();
    }

    default <E extends Item> T save(E item) {
        return getItemRepo().save(convertToT(item, item.getItemClazz()));
    }

    default <E extends Item> void delete(E item) {
        getItemRepo().delete(convertToT(item, item.getItemClazz()));
    }

    <E extends Item> T convertToT(E item, Class<?> clazz);


}
