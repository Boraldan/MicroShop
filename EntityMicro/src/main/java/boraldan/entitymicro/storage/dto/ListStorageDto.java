package boraldan.entitymicro.storage.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.storage.entity.Storage;
import lombok.Data;

import java.util.List;

@Data
public class ListStorageDto {
    private Class<?> storageClazz;
    private CategoryName categoryName;
    private List<Storage> storageList;
    private List<Long> itemIdList;

}
