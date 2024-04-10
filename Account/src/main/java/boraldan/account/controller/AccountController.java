package boraldan.account.controller;


import boraldan.account.service.i_service.CustomerService;
import boraldan.account.service.i_service.PersonService;
import boraldan.entitymicro.account.entity.person.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final CustomerService customerService;


    @GetMapping
    public ResponseEntity<?> getCustomerAccount() {
        // проходит авторизацию через Oauth2

        Customer customer = customerService.findBiId(1L);


    }


}
