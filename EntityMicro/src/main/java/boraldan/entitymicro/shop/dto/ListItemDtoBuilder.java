package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.item.Item;

import java.util.List;

public class ListItemDtoBuilder {

    private final ListItemDto listItemDto;

    public ListItemDtoBuilder() {
        this.listItemDto = new ListItemDto();
    }

    public ListItemDtoBuilder setItemList(List<Item> itemList) {
        this.listItemDto.setItemList(itemList);
        return  this;
    }

    public ListItemDto build(){
        return listItemDto;
    }
}
