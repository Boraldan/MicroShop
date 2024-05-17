package boraldan.cart.repository;

import boraldan.entitymicro.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepo extends JpaRepository<Cart, UUID> {

    Optional<Cart> findByCustomerId(UUID customerId);
}
