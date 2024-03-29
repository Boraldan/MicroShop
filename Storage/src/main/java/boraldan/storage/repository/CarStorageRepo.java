package boraldan.storage.repository;


import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import org.springframework.stereotype.Repository;

@Repository
public interface CarStorageRepo extends StorageJpaRepository<CarStorage> {

}
