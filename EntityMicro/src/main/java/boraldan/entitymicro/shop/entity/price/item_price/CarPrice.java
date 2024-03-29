package boraldan.entitymicro.shop.entity.price.item_price;

import boraldan.entitymicro.shop.entity.price.Price;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "car_price")
public class CarPrice extends Price {

//    @Override
//    public CarPrice clone() throws CloneNotSupportedException {
//        return (CarPrice) super.clone();
//    }

}
