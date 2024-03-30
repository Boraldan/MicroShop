package boraldan.entitymicro.storage.entity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StorageDto {
    private UUID id;
    private long quantity;
    private long reserve;
    private Class<?> clazz;

}
