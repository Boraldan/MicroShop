package boraldan.shop.service.i_service;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.specification.SpecItem;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.repository.specifications.ItemSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemUnifiedService<T extends Item, R extends ItemUnifiedRepo<T>> {

    R getItemRepo();

    default Optional<T> findFirst() {
        return getItemRepo().findFirstByOrderByIdAsc();
    }

    default Optional<T> getById(UUID id) {
        return getItemRepo().findById(id);
    }

    default Optional<T> findFirstByCategoryName(CategoryName categoryName) {
        return getItemRepo().findFirstByCategoryCategoryName(categoryName);
    }

    default List<T> getAll() {
        return getItemRepo().findAll();
    }

    default Page<T> getAllBySpecification(SpecItem specItem, Pageable pageable) {
        Specification<T> spec = Specification.where(null);
        if (specItem.getMinPrice() != null) {
            spec = spec.and(ItemSpecification.scoreGreaterOrEqualsThan(specItem.getMinPrice()));
        }
        if (specItem.getMaxPrice() != null) {
            spec = spec.and(ItemSpecification.scoreLessThanOrEqualsThan(specItem.getMaxPrice()));
        }
        if (specItem.getPartName() != null) {
            spec = spec.and(ItemSpecification.nameLike(specItem.getPartName()));
        }
        return getItemRepo().findAll(spec, pageable);
    }

    default Page<T> getAllByParam(SpecItem specItem, Pageable pageable) {
        return getItemRepo().findAllByParam(specItem.getPartName(), specItem.getMinPrice(), specItem.getMaxPrice(), pageable);
    }

    default List<T> getByUuidList(List<UUID> uuidList){
        return getItemRepo().findAllById(uuidList);
    }

    default <E extends Item> T save(E item) {
        return getItemRepo().save(convertToT(item, item.getItemClazz()));
    }

    default void deleteById(UUID id) {
        getItemRepo().deleteById(id);
    }

    default <E extends Item> void delete(E item) {
        getItemRepo().delete(convertToT(item, item.getItemClazz()));
    }

    <E extends Item> T convertToT(E item, Class<?> clazz);


}
