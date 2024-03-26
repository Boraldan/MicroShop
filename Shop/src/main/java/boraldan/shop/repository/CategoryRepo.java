package boraldan.shop.repository;


import boraldan.entitymicro.global.category.Category;
import boraldan.entitymicro.global.category.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(CategoryName categoryName);
}
