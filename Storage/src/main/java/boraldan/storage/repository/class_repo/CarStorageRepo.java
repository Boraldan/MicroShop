package boraldan.storage.repository.class_repo;


import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.GlobalJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarStorageRepo extends GlobalJpaRepository<CarStorage> {

}
