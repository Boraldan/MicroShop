package boraldan.entitymicro.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LotDto {

    private UUID itemId;
    private Integer quantity;
    private Integer limitQuantity;

}
