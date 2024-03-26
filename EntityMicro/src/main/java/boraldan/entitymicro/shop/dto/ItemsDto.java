package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.item.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemsDto {

    List<Item> items;
}
