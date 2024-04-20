package boraldan.account.controller;

import boraldan.account.keycloak.KeycloakService;
import boraldan.account.service.i_service.CustomerService;
import boraldan.account.validate.SingUpDtoValidate;
import boraldan.entitymicro.account.dto.SingUpDto;
import boraldan.entitymicro.account.entity.person.Customer;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/authorize")
@RestController
public class AuthorizeController {

    private final ModelMapper modelMapper;
    private final CustomerService customerService;
    private final KeycloakService keycloakService;
    private final SingUpDtoValidate singUpDtoValidate;


    @PostMapping("/singup")
    public ResponseEntity<?> singUp(@RequestBody SingUpDto singUpDto) {
        ResponseEntity<?> responseEntity = singUpDtoValidate.myValidate(singUpDto);
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        }
        Response responseJakarta = keycloakService.createKeycloakUser(singUpDto);
        if (responseJakarta.getStatus() != 201) {
            return new ResponseEntity<>("User not created ", HttpStatus.CONFLICT);
        }
        Customer customer = customerService.saveNew(convertToCustomer(singUpDto));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    private Customer convertToCustomer(SingUpDto singUpDto) {
        return modelMapper.map(singUpDto, Customer.class);
    }
}
