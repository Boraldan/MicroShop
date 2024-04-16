package boraldan.account.validate;

import boraldan.entitymicro.account.dto.PersonDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonDtoValid implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO personDTO = (PersonDTO) target;

////         проверка на обязательные параметры
//        if (!personDTO.getId().isBlank()) {
//            return new ResponseEntity("redundant param: id MUST be empty", HttpStatus.NOT_ACCEPTABLE);
//        }
//////         если передали пустое значение
//        if (userDTO.getEmail() == null || userDTO.getEmail().trim().length() == 0) {
//            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        if (userDTO.getPassword() == null || userDTO.getPassword().trim().length() == 0) {
//            return new ResponseEntity("missed param: password", HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        if (userDTO.getUsername() == null || userDTO.getUsername().trim().length() == 0) {
//            return new ResponseEntity("missed param: username", HttpStatus.NOT_ACCEPTABLE);
//        }


//        PersonDTO personDTO = (PersonDTO) target;
//        Person person = personService.findById(personDTO.getId());
//        if (personService.findByCard(personDTO.getCard()).isPresent() && personDTO.getId() != person.getId()) {
//            errors.rejectValue("card", "", "Пользователь с такой картой существует");
//        }
//        if (personService.findByPhone(personDTO.getPhone()).isPresent() && personDTO.getId() != person.getId()) {
//            errors.rejectValue("phone", "", "Пользователь с таким телефоном существует");
//        }
//        if (personService.findByEmail(personDTO.getEmail()).isPresent() && personDTO.getId() != person.getId()) {
//            errors.rejectValue("email", "", "Пользователь с таким email существует");
//        }
//

    }
}
