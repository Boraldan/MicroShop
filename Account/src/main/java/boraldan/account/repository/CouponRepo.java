package boraldan.account.repository;


import boraldan.entitymicro.account.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, UUID> {

    Optional<Coupon> findFirstByCouponNameAndValid (String name, boolean valid);
}
