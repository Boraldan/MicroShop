package boraldan.account.controller;


import boraldan.account.keycloak.KeycloakService;
import boraldan.account.service.i_service.CustomerService;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Log4j2
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final CustomerService customerService;
    private final KeycloakService keycloakService;
    private final ModelMapper modelMapper;


    @GetMapping("/customer")
    public ResponseEntity<?> getCustomerAccount(Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName()).get();
        return ResponseEntity.ok(customer);

    }

    @PostMapping("/getbyusername")
    public ResponseEntity<Customer> getCustomerByUsername(@RequestBody String username) {
        Customer customer = customerService.findByUsername(username).get();
        System.out.println( "AccountController   --> " +  customer);
        return ResponseEntity.ok(customer);

    }

}
