package boraldan.entitymicro.storage.entity.dto;

import boraldan.entitymicro.storage.entity.Storage;
import lombok.Data;

import java.util.List;

@Data
public class StorageAllDto {
    List<Storage> list;
}
