package boraldan.shop.service.i_service;

import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;

import java.util.Optional;

public interface CategoryService {

   Optional<Category> findByCategoryName(CategoryName categoryName);

}
