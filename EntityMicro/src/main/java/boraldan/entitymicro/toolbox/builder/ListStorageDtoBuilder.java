package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.Storage;

import java.util.List;
import java.util.UUID;

public class ListStorageDtoBuilder {

    private final ListStorageDto listStorageDto;

    private ListStorageDtoBuilder() {
        this.listStorageDto = new ListStorageDto();
    }

    public static ListStorageDtoBuilder creat(){
        return new ListStorageDtoBuilder();
    }

    public ListStorageDtoBuilder setStorageClazz(Class<? extends Storage> storageClazz) {
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
