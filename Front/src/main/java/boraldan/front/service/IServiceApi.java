package boraldan.front.service;


import boraldan.entitymicro.cart.dto.CartsDTO;
import boraldan.entitymicro.shop.dto.CarDto;
import boraldan.entitymicro.shop.dto.ItemsDto;
import boraldan.entitymicro.test.Fly;
import boraldan.entitymicro.test.Lot;

public interface IServiceApi {
    Lot getLot();

    CarDto getCar();

    Fly getFly();

    ItemsDto getItems();
    CartsDTO getCarts();

    String getCard();

    String getSession(String sessionId);
}
