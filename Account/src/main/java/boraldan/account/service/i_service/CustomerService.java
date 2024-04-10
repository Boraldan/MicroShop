package boraldan.account.service.i_service;

import boraldan.entitymicro.account.entity.person.Customer;

public interface CustomerService {

    Customer findBiId(Long id);

    Customer save(Customer customer);

    void delete(Customer customer);
}
