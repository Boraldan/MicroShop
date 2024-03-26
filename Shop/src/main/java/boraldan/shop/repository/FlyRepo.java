package boraldan.shop.repository;

import boraldan.entitymicro.test.Fly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlyRepo extends JpaRepository<Fly, Long> {
}
