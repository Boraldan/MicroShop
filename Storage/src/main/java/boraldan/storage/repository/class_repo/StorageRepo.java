package boraldan.storage.repository.class_repo;

import boraldan.entitymicro.storage.entity.Storage;
import boraldan.storage.repository.GlobalJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepo extends GlobalJpaRepository<Storage> {

}
