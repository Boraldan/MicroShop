package boraldan.entitymicro.storage.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReserveDto {

    private UUID itemId;
    private Integer cartReserve;

}
