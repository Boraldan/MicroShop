package boraldan.order.repository;


import boraldan.entitymicro.order.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findFirstByNameAndValid(String name, boolean valid);
}
