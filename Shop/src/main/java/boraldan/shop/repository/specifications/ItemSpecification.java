package boraldan.shop.repository.specifications;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.Price;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ItemSpecification {
    public static <T extends Item> Specification<T> scoreGreaterOrEqualsThan(BigDecimal price) {
//      Использование root.join:
//    Преимущества: Объединение сущностей может помочь оптимизировать выполнение запроса, особенно если у вас есть
//    несколько связанных сущностей. Hibernate может оптимизировать запрос и выполнить единственный запрос к базе
//    данных, объединяя данные из нескольких таблиц.
//    Недостатки: В случае использования join, может быть создано больше промежуточных данных, и это может привести
//    к дополнительным затратам на производительность. Также учитывайте, что join может быть менее эффективным для
//    больших объемов данных или в случае отсутствия индексов.
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Item, Price> priceJoin = root.join("price");
            return criteriaBuilder.greaterThanOrEqualTo(priceJoin.get("basePrice"), price);
        };
//    Использование корректного пути (root.get):
//    Преимущества: Использование простого root.get может быть проще и более понятно для чтения кода. Этот способ
//    также может быть предпочтительным в случае простых запросов или небольших объемов данных.
//    Недостатки: В случае комплексных запросов или запросов с несколькими связями между таблицами, использование
//    только root.get может привести к большему количеству запросов к базе данных и медленной производительности.
//        return (root, query, criteriaBuilder) -> {
//            Join<Item, Price> priceJoin = root.join("price");
//            return criteriaBuilder.greaterThanOrEqualTo(priceJoin.get("basePrice"), price);
//        };
    }

    public static <T extends Item> Specification<T> scoreLessThanOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
//        return (root, criteriaQuery, criteriaBuilder) -> {
//            Join<Item, Price> priceJoin = root.join("price");
//            return criteriaBuilder.lessThanOrEqualTo(priceJoin.get("basePrice"), price);
//        };
    }

    public static <T extends Item> Specification<T> nameLike(String namePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", namePart));
    }

//    public static Specification<Item> categoryLike(Integer idCategory) {
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), idCategory);
//    }
}
