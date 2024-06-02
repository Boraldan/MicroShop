package boraldan.entitymicro.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LotDto {

    private UUID itemId;
    private String itemClassName;

    private Integer lotQuantity;
    private Integer limitQuantity;

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public void setItemClassName(String itemClassName) {
        this.itemClassName = itemClassName;
    }

    public void setLotQuantity(Integer lotQuantity) {
        this.lotQuantity = lotQuantity;
    }

    public void setLimitQuantity(Integer limitQuantity) {
        this.limitQuantity = limitQuantity;
    }
}
