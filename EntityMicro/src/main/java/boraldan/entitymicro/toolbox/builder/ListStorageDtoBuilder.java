package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.storage.dto.StorageListDto;
import boraldan.entitymicro.storage.entity.Storage;

import java.util.List;
import java.util.UUID;

public class ListStorageDtoBuilder {

    private final StorageListDto storageListDto;

    private ListStorageDtoBuilder() {
        this.storageListDto = new StorageListDto();
    }

    public static ListStorageDtoBuilder creat(){
        return new ListStorageDtoBuilder();
    }

    public ListStorageDtoBuilder setStorageClazz(Class<? extends Storage> storageClazz) {
        this.storageListDto.setStorageClazz(storageClazz);
        return this;
    }

    public ListStorageDtoBuilder setStorageCategory(CategoryName categoryName) {
        this.storageListDto.setCategoryName(categoryName);
        return this;
    }

    public ListStorageDtoBuilder setStorageList(List<?> storageList) {
        this.storageListDto.setStorageList(storageList.stream().map(el -> (Storage) el).toList());
        return this;
    }

    public ListStorageDtoBuilder setItemIdList(List<UUID> itemIdList) {
        this.storageListDto.setItemIdList(itemIdList);
        return this;
    }

    public StorageListDto build() {
        return storageListDto;
    }
}
