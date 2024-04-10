package boraldan.account.repository.order;

import boraldan.entitymicro.account.entity.order.UnitOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnitOrderRepo extends JpaRepository<UnitOrder, UUID> {
}
