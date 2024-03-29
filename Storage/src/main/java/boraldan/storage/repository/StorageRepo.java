package boraldan.storage.repository;

import boraldan.entitymicro.storage.entity.Storage;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends StorageJpaRepository<Storage> {
}
