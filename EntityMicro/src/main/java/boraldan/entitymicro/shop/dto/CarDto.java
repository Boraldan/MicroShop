package boraldan.entitymicro.shop.dto;

import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.item.transport.car.Types;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import lombok.Data;

@Data
public class CarDto {

    private String name;

    private CarPrice price;

    private String factory;

    private Integer year;

    private Types types;

    private Fuel fuel;

//    private List<String> images;

    private CategoryName categoryName;

    private long quantity;


}
