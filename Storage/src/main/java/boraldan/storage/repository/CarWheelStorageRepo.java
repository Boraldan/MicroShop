package boraldan.storage.repository;

import boraldan.entitymicro.storage.entity.transport.car_relate.wheel.CarWheelStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CarWheelStorageRepo extends JpaRepository<CarWheelStorage, UUID> {

    Optional<CarWheelStorage> findByItemId(Long itemId);

}
