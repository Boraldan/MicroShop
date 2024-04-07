package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.dto.ListStorageDtoBuilder;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.shop.controller.feign.StorageFeign;
import boraldan.shop.repository.CategoryRepo;
import boraldan.shop.service.i_service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceV1 implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final StorageFeign storageFeign;

    @Override
    public Optional<Category> getCategoryByCategoryName(CategoryName categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }

    @Override
    public List<Item> getListByCategoryName(CategoryName categoryName) {
        Category category = categoryRepo.findByCategoryName(categoryName).orElse(null);
        if (category != null && !category.getItems().isEmpty()) {
            List<UUID> uuidList = category.getItems().stream().map(Item::getStorageId).toList();
            ListStorageDto listStorageDto = storageFeign.getListByCategory(new ListStorageDtoBuilder()
                    .setStorageCategory(categoryName).setUuidList(uuidList).build()).getBody();

            if (listStorageDto == null) return category.getItems();

            return addStorageToListT(category.getItems(), listStorageDto);
        }
        return new ArrayList<>();
    }

    private List<Item> addStorageToListT(List<Item> itemList, ListStorageDto listStorageDto) {
        Map<UUID, Storage> storageMap = new HashMap<>();
        for (Storage storage : listStorageDto.getStorageList()) {
            storageMap.put(storage.getId(), storage);
        }
        return itemList.stream().map(el -> {
            el.setStorage(storageMap.get(el.getStorageId()));
            return el;
        }).toList();
    }
}
