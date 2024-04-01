package boraldan.entitymicro.storage.entity.dto;

import boraldan.entitymicro.storage.entity.Storage;
import lombok.Data;

import java.util.List;

@Data
public class ListStorageDto {
    private Class<?> clazz;
    private List<Storage> list;
}
