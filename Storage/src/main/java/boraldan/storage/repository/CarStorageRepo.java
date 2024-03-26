package boraldan.storage.repository;


import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarStorageRepo extends JpaRepository<CarStorage, Long> {
    Optional<CarStorage> findByItemId(Long itemId);
}
