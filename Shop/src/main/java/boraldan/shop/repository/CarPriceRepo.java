package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.price.CarPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPriceRepo extends JpaRepository<CarPrice, Long> {
}
