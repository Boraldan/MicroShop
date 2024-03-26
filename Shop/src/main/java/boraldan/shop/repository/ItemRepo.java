package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByCategory_CategoryName(CategoryName categoryName);
}
