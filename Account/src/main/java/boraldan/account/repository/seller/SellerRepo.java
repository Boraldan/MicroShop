package boraldan.account.repository.seller;

import boraldan.entitymicro.account.entity.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerRepo extends JpaRepository<Seller, UUID> {
}
