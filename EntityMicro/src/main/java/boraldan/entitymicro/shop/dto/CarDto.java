package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.item.transport.car.Types;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.storage.entity.Storage;
import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String name;
    private String factory;
    private Integer year;
    private Types types;
    private Fuel fuel;

    private CategoryName categoryName;
    private CarPrice price;
    private Storage storage;


}
