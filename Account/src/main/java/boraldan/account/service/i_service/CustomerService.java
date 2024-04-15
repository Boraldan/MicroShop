package boraldan.account.service.i_service;

import boraldan.entitymicro.account.entity.person.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Customer findById(UUID id);

    Optional<Customer> findByUsername(String username);

    Customer save(Customer customer);

    void delete(Customer customer);
}
