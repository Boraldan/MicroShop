package boraldan.front.service;


import boraldan.entitymicro.cart.dto.CartsDTO;
import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.test.Fly;
import boraldan.entitymicro.test.Lot;

import java.util.List;

public interface IServiceApi {
    Lot getLot();

//    CarDto getCar();

    Fly getFly();

//    ListItemDto getItems(Category category);
    List<Item> getItems(Category category);
    CartsDTO getCarts();

    String getCard();

    String getSession(String sessionId);
}
