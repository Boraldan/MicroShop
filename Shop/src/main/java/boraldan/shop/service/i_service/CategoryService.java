package boraldan.shop.service.i_service;

import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

   Optional<Category> getCategoryByCategoryName(CategoryName categoryName);

   List<Item> getListByCategoryName(CategoryName categoryName);

}
