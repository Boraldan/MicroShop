package boraldan.storage.repository;

import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;

import java.util.Optional;


public interface BikeStorageRepo extends StorageJpaRepository<BikeStorage> {
    Optional<BikeStorage> findByItemId(Long itemId);
}
