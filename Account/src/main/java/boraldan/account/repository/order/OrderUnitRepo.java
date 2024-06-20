package boraldan.account.repository.order;

import boraldan.entitymicro.account.entity.order.OrderUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderUnitRepo extends JpaRepository<OrderUnit, UUID> {
}
