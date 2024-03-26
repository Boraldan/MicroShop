package boraldan.cart.repository;

import boraldan.entitymicro.cart.entity.CartUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartUnitRepo extends JpaRepository<CartUnit, Long> {
}
