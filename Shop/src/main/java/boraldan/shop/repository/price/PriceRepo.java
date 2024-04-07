package boraldan.shop.repository.price;

import boraldan.entitymicro.shop.entity.price.Price;
import boraldan.shop.repository.PriceUnifiedRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends PriceUnifiedRepo<Price> {
}
