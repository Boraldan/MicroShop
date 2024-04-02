package boraldan.storage.repository.class_repo;

import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeStorageRepo extends GlobalJpaRepository<BikeStorage> {
}
