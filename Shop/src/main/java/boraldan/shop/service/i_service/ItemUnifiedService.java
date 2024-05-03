package boraldan.shop.service.i_service;

import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.shop.repository.ItemUnifiedRepo;

import java.util.List;
import java.util.UUID;

public interface ItemUnifiedService<T extends Item, R extends ItemUnifiedRepo<T>> {

    R getItemRepo();

    default T getById(UUID id) {
        return getItemRepo().findById(id).orElse(null);
    }

    default List<T> getAll() {
        return getItemRepo().findAll();
    }

    default <E extends Item> T save(E item) {
        return getItemRepo().save(convertToT(item, item.getItemClazz()));
    }

    default void deleteById(UUID id){
        getItemRepo().deleteById(id);
    }

    default <E extends Item> void delete(E item) {
        getItemRepo().delete(convertToT(item, item.getItemClazz()));
    }

    <E extends Item> T convertToT(E item, Class<?> clazz);



}
