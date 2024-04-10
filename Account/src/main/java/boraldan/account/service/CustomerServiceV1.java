package boraldan.account.service;

import boraldan.account.repository.person.CustomerRepo;
import boraldan.account.service.i_service.CustomerService;
import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceV1 implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer findBiId(Long id) {

        Customer customer =  customerRepo.findById(id).orElse(null);
        if (customer != null) {
            List<Order> orderList =customer.getOrders();




        }

        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }
}
