package boraldan.entitymicro.storage.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.storage.entity.Storage;

import java.util.List;
import java.util.UUID;

public class ListStorageDtoBuilder {

    private final ListStorageDto listStorageDto;

    public ListStorageDtoBuilder() {
        this.listStorageDto = new ListStorageDto();
    }

    public ListStorageDtoBuilder setStorageClazz(Class<?> storageClazz) {
        this.listStorageDto.setStorageClazz(storageClazz);
        return this;
    }

    public ListStorageDtoBuilder setStorageCategory(CategoryName categoryName) {
        this.listStorageDto.setCategoryName(categoryName);
        return this;
    }

    public ListStorageDtoBuilder setStorageList(List<?> storageList) {
        this.listStorageDto.setStorageList(storageList.stream().map(el -> (Storage) el).toList());
        return this;
    }

    public ListStorageDtoBuilder setItemIdList(List<UUID> itemIdList) {
        this.listStorageDto.setItemIdList(itemIdList);
        return this;
    }

    public ListStorageDto build() {
        return listStorageDto;
    }
}
