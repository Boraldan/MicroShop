package boraldan.shop.repository.price;

import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.shop.repository.PriceUnifiedRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface BikePriceRepo extends PriceUnifiedRepo<BikePrice> {
}
