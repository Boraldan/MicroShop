package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikePriceRepo extends JpaRepository<BikePrice, Long> {
}
