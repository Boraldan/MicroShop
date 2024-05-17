package boraldan.account.service;

import boraldan.account.controller.feign.CartFeign;
import boraldan.account.keycloak.KeycloakService;
import boraldan.account.repository.person.CustomerRepo;
import boraldan.account.service.i_service.CustomerService;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerServiceV1 implements CustomerService {

    private final CustomerRepo customerRepo;
    private final KeycloakService kcService;
    private final CartFeign cartFeign;

    @Override
    public Customer findById(UUID id) {

        Customer customer =  customerRepo.findById(id).orElse(null);
//        if (customer != null) {
//            List<Order> orderList =customer.getOrders();
//        }
        return customer;
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
          return customerRepo.findByUsernameIgnoreCase(username);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Transactional
    @Override
    public Customer saveNew(Customer customer) {
//        customer.setCartId(cartFeign.getNewCart().getBody().getId());
        return customerRepo.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }
}
