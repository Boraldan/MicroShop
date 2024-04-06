package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.shop.repository.CategoryRepo;
import boraldan.shop.service.i_service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceV1 implements CategoryService {

    private final CategoryRepo categoryRepo;
    @Override
    public Optional<Category> findByCategoryName(CategoryName categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }
}
