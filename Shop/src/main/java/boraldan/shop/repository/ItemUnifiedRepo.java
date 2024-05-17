package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface ItemUnifiedRepo<T extends Item> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {

    Optional<T> findFirstByCategoryCategoryName(CategoryName categoryName);

    Optional<T> findFirstByOrderByIdAsc();

    @Query("SELECT t FROM Item t where " +
            "(:title is null or :title='' or lower(t.title) like lower(concat('%', :title,'%'))) and" +
            "(:minPrice is null or t.price.customPrice>=:minPrice) and " +
            "(:maxPrice is null or t.price.customPrice<=:maxPrice)")
    Page<T> findAllByParam(@Param("title") String title,
                           @Param("minPrice") BigDecimal minPrice,
                           @Param("maxPrice") BigDecimal maxPrice,
                           Pageable pageable);


}
