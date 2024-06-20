package boraldan.account.repository.person;

import boraldan.entitymicro.account.entity.person.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByUsernameIgnoreCase(String username);

    @EntityGraph(attributePaths = {"orders"})
    @Query("SELECT c FROM Customer c WHERE LOWER(c.username) = LOWER(:username)")
    Optional<Customer> fetchCustomerWithOrdersByUsername(@Param("username") String username);


// region -->  варианты запросов
//    @EntityGraph(attributePaths = {"orders"})
//    Optional<Customer> findByUsernameIgnoreCaseAndActive(String username, boolean active);
//
//    @EntityGraph(attributePaths = {"orders", "addresses"})
//    Optional<Customer> findByUsernameIgnoreCase(String username);
//endregion
}
