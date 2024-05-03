package boraldan.shop.repository.item;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.shop.repository.ItemUnifiedRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends ItemUnifiedRepo<Item> {

    List<Item> findByCategory_CategoryName(CategoryName categoryName);

}
