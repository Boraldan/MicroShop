package boraldan.account.validate;

import boraldan.account.keycloak.KeycloakService;
import boraldan.entitymicro.account.dto.SingUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SingUpDtoValidate implements Validator {

    private final KeycloakService kcService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SingUpDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

//    @Override
//    public void validate(Object target, Errors errors) {
//        SingUpDto singUpDto = (SingUpDto) target;
//        if (!kcService.getListByUsername(singUpDto.getUsername()).isEmpty()) {
//            errors.rejectValue("username", "", "Пользователь с такой username существует");
//            return;
//        }
//        if (!kcService.getListByEmail(singUpDto.getEmail()).isEmpty()) {
//            errors.rejectValue("email", "", "Пользователь с таким email существует");
//        }
//    }


    public ResponseEntity<?> myValidate(SingUpDto singUpDto) {

        if (!kcService.getListByUsername(singUpDto.getUsername()).isEmpty()) {
            return new ResponseEntity<>("missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }
        if (!kcService.getListByEmail(singUpDto.getEmail()).isEmpty()) {
            return new ResponseEntity<>("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok().build();
    }
}
