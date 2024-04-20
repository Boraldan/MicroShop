package boraldan.account.repository.person;

import boraldan.entitymicro.account.entity.person.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByUsernameIgnoreCase(String username);

}
