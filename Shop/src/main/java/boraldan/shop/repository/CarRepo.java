package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {


}
