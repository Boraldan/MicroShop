package boraldan.shop.repository.specifications;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.Price;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ItemSpecification {
    public static <T extends Item> Specification<T> scoreGreaterOrEqualsThan(BigDecimal price) {
//      Использование root.join: Hibernate может оптимизировать запрос и выполнить единственный запрос к базе
//    данных, объединяя данные из нескольких таблиц.
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Item, Price> priceJoin = root.join("price");
            return criteriaBuilder.greaterThanOrEqualTo(priceJoin.get("customPrice"), price);
        };
//      root.get может привести к большему количеству запросов к базе данных
//        return (root, query, criteriaBuilder) -> {
//            Join<Item, Price> priceJoin = root.join("price");
//            return criteriaBuilder.greaterThanOrEqualTo(priceJoin.get("basePrice"), price);
//        };
    }

    public static <T extends Item> Specification<T> scoreLessThanOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Item, Price> priceJoin = root.join("price");
            return criteriaBuilder.lessThanOrEqualTo(priceJoin.get("customPrice"), price);
        };
    }

    public static <T extends Item> Specification<T> nameLike(String namePart) {
        // не игнорирует регистр при поиске
        // return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", namePart));

        // игнорирует регистр при поиске
        return (root, query, criteriaBuilder) -> {
            String formattedNamePart = String.format("%%%s%%", namePart.toLowerCase());
            Expression<String> lowerTitle = criteriaBuilder.lower(root.get("title"));
            return criteriaBuilder.like(lowerTitle, formattedNamePart);
        };
    }

}
