package boraldan.shop.repository.item;

import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.shop.repository.ItemUnifiedRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepo extends ItemUnifiedRepo<Bike> {
}
