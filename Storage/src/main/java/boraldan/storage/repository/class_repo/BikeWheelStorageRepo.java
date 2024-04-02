package boraldan.storage.repository.class_repo;

import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeWheelStorageRepo extends GlobalJpaRepository<BikeWheelStorage> {

}
