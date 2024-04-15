package boraldan.account.repository.seller;

import boraldan.entitymicro.account.entity.seller.SoleTrader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SoleTraderRepo extends JpaRepository<SoleTrader, UUID> {
}
