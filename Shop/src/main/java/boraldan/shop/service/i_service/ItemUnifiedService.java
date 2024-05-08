package boraldan.shop.service.i_service;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.shop.repository.ItemUnifiedRepo;
import boraldan.shop.repository.specifications.ItemSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
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

    default Page<T> getAllBySpecification(BigDecimal minScore, BigDecimal maxScore, String partName, Integer page) {
        Specification<T> spec = Specification.where(null);
        if (minScore != null) {
            spec = spec.and(ItemSpecification.scoreGreaterOrEqualsThan(minScore));
        }
        if (maxScore != null) {
            spec = spec.and(ItemSpecification.scoreLessThanOrEqualsThan(maxScore));
        }
        if (partName != null) {
            spec = spec.and(ItemSpecification.nameLike(partName));
        }
//        if (nameCategory != null) {
//            Integer idCategory = categoryService.getIdCategoryByName(nameCategory);
//            spec = spec.and(ProductSpecifications.categoryLike(idCategory));
//        }
        return getItemRepo().findAll(spec, PageRequest.of(page - 1, 10));
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
