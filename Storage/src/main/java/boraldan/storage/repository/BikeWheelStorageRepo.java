package boraldan.storage.repository;

import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface BikeWheelStorageRepo extends JpaRepository<BikeWheelStorage, UUID> {

    Optional<BikeWheelStorage> findByItemId(Long itemId);
}
