package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepo extends JpaRepository<Bike, Long> {
}
