package boraldan.entitymicro.storage.entity.dto;

import boraldan.entitymicro.storage.entity.Storage;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ListStorageDto {
    private Class<?> storageClazz;
    private List<Storage> storageList;
    private List<UUID> uuidList;

}
