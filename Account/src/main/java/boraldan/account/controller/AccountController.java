package boraldan.account.controller;


import boraldan.account.service.i_service.CustomerService;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class AccountController {

    private final CustomerService customerService;

//    @GetMapping
//    public ResponseEntity<?> getCustomerAccount(Principal principal) {
//        log.info(principal == null ? "НЕТ principal" : principal);
//        Customer customer = customerService.findByUsername(principal.getName()).get();
//        return ResponseEntity.ok(customer);
//    }

    @GetMapping("addcustomer")
    public ResponseEntity<?> addCustomer() {
        Customer customer = new Customer();
        customer.setUsername("tom");
        customer.setAge(21);
        customer.setFio("Tom Soer");
        customer.setPhone(91112345L);
        customer.setEmail("tom@mail.ru");
        customer = customerService.save(customer);
        return ResponseEntity.ok(customer);

    }


}
