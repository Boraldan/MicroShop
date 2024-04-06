package boraldan.shop.repository.price;

import boraldan.entitymicro.shop.entity.price.item_price.CarWheelPrice;
import boraldan.shop.repository.PriceUnifiedRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface CarWheelPriceRepo extends PriceUnifiedRepo<CarWheelPrice> {
}
