package boraldan.account.controller;


import boraldan.account.service.i_service.CustomerService;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Log4j2
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerAccount(Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName()).orElse(null);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer/orders")
    public ResponseEntity<Customer> getCustomerWhitOrder(Principal principal) {
        Customer customer = customerService.getCustomerWhitOrder(principal.getName()).orElse(null);
        return ResponseEntity.ok(customer);
    }


}
