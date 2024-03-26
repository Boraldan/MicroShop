package boraldan.storage.repository;

import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeStorageRepo extends JpaRepository<BikeStorage, Long> {
    Optional<BikeStorage> findByItemId(Long itemId);
}
