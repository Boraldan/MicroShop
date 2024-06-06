package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.dto.ItemListDto;
import boraldan.entitymicro.shop.entity.item.Item;

import java.util.List;

public class ItemDtoListBuilder {

    private final ItemListDto itemListDto;

    public ItemDtoListBuilder() {
        this.itemListDto = new ItemListDto();
    }

    public ItemDtoListBuilder setItemList(List<Item> itemList) {
        this.itemListDto.setItemList(itemList);
        return  this;
    }

    public ItemListDto build(){
        return itemListDto;
    }
}
