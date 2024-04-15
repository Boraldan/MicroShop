package boraldan.account.controller;


import boraldan.account.dto.Counter;
import boraldan.entitymicro.account.dto.UserDTO;
import boraldan.account.keycloak.KeycloakUtils;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/*

Чтобы дать меньше шансов для взлома (например, CSRF атак): POST/PUT запросы могут изменять/фильтровать закрытые данные, а GET запросы - для получения незащищенных данных
Т.е. GET-запросы не должны использоваться для изменения/получения секретных данных

Если возникнет exception - вернется код  500 Internal Server Error, поэтому не нужно все действия оборачивать в try-catch

Используем @RestController вместо обычного @Controller, чтобы все ответы сразу оборачивались в JSON,
иначе пришлось бы добавлять лишние объекты в код, использовать @ResponseBody для ответа, указывать тип отправки JSON

Названия методов могут быть любыми, главное не дублировать их имена и URL mapping

*/
@Log4j2
@AllArgsConstructor
@RequestMapping("/kc") // базовый URI
@RestController
public class KeyCloakController {

    public static final String ID_COLUMN = "id"; // имя столбца id
    private static final int CONFLICT = 409; // если пользователь уже существует в KC и пытаемся создать такого же
    private static final String USER_ROLE_NAME = "ROLE_CUSTOMER"; // название роли из KC
    private final KeycloakUtils keycloakUtils;
    private final ModelMapper modelMapper;

//    @PostMapping("/add")
//    public ResponseEntity<?> add(@RequestBody UserDTO userDTO) {

    @GetMapping("/add")
    public ResponseEntity<?> add() {
        UserDTO userDTO = new UserDTO();
        int num = Counter.getNum();
        userDTO.setUsername("Gek_%d".formatted(num));
        userDTO.setFio("firstName_%d".formatted(num));
        userDTO.setEmail("gek_%d@mail.ru".formatted(num));
        userDTO.setPassword("123");

        System.out.println(userDTO);

        // проверка на обязательные параметры
//        if (!userDTO.getId().isBlank()) {
//            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
//            return new ResponseEntity("redundant param: id MUST be empty", HttpStatus.NOT_ACCEPTABLE);
//        }

        // если передали пустое значение
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


        // создаем пользователя
        Response response = keycloakUtils.createKeycloakUser(userDTO);

        System.out.println(response.getStatus());

        if (response.getStatus() == CONFLICT) {
            return new ResponseEntity("User or email already exists " + userDTO.getEmail(), HttpStatus.CONFLICT);
        }

        // получаем его ID
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);

        List<String> defaultRoles = new ArrayList<>();
        defaultRoles.add(USER_ROLE_NAME); // эта роль должна присутствовать в KC на уровне Realm
//        defaultRoles.add("admin");

        keycloakUtils.addRoles(userId, defaultRoles);

//        return ResponseEntity.status(createdResponse.getStatus()).build();
        return ResponseEntity.ok(userDTO);

    }


    // обновление
    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {

        // проверка на обязательные параметры
        if (userDTO.getId().isBlank()) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

//        // если передали пустое значение
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


        // save работает как на добавление, так и на обновление
        keycloakUtils.updateKeycloakUser(userDTO);

        return new ResponseEntity<>(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)

    }


    // для удаления используем типа запроса put, а не delete, т.к. он позволяет передавать значение в body, а не в адресной строке
    @PostMapping("/deletebyid")
    public ResponseEntity<Void> deleteByUserId(@RequestBody String userId) {

//        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
//        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
//        try {
//            userService.deleteByUserId(userId);
//        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
//            return new ResponseEntity("userId=" + userId + " not found", HttpStatus.NOT_ACCEPTABLE);
//        }

        keycloakUtils.deleteKeycloakUser(userId);

        return ResponseEntity.ok().build();

    }

//    // для удаления используем типа запроса put, а не delete, т.к. он позволяет передавать значение в body, а не в адресной строке
//    @PostMapping("/deletebyemail")
//    public ResponseEntity deleteByUserEmail(@RequestBody String email) {
//
//        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
//        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
//        try {
//            userService.deleteByUserEmail(email);
//        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
//            return new ResponseEntity("email=" + email + " not found", HttpStatus.NOT_ACCEPTABLE);
//        }
//        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
//    }


    // получение объекта по id
//    @PostMapping("/id")
    @GetMapping("/id")
//    public ResponseEntity<String> findById(@RequestBody String userId) {
    public ResponseEntity<UserDTO> findById() {

//        Optional<User> userOptional = userService.findById(id);
//
//        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
//        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
//        try {
//            if (userOptional.isPresent()) { // если объект найден
//                return ResponseEntity.ok(userOptional.get()); // получаем User из контейнера и возвращаем в теле ответа
//            }
//        } catch (NoSuchElementException e) { // если объект не будет найден
//            e.printStackTrace();
//        }

//        return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
//        return ResponseEntity.ok(keycloakUtils.findUserById(userId));
        log.info("@GetMapping   1 ");
        UserDTO userDTO = this.convertToUserDTO(keycloakUtils.findUserById("d40adf39-d573-4ed7-aa7a-36542e1c79f2"));

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(keycloakUtils.getAllUser());
    }


    // получение уникального объекта по email
    @PostMapping("/search")
    public ResponseEntity<List<UserRepresentation>> search(@RequestBody String email) { // строго соответствие email

//        User user;
//
//        // можно обойтись и без try-catch, тогда будет возвращаться полная ошибка (stacktrace)
//        // здесь показан пример, как можно обрабатывать исключение и отправлять свой текст/статус
//        try {
//            user = userService.findByEmail(email);
//        } catch (NoSuchElementException e) { // если объект не будет найден
//            e.printStackTrace();
//            return new ResponseEntity("email=" + email + " not found", HttpStatus.NOT_ACCEPTABLE);
//        }
        return ResponseEntity.ok(keycloakUtils.searchKeycloakUsers(email));

    }


    // поиск по любым параметрам UserSearchValues
//    @PostMapping("/search")
//    public ResponseEntity<List<UserRepresentation>> search(@RequestBody String email) throws ParseException {
//
//        // все заполненные условия проверяются условием ИЛИ - это можно изменять в запросе репозитория
//
//        // можно передавать не полный email, а любой текст для поиска
//        String email = userSearchValues.getEmail() != null ? userSearchValues.getEmail() : null;
//
//        String username = userSearchValues.getUsername() != null ? userSearchValues.getUsername() : null;
//
//        // проверка на обязательные параметры - если они нужны по задаче
//        if (email == null || email.trim().length() == 0) {
//            return new ResponseEntity("missed param: user email", HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        String sortColumn = userSearchValues.getSortColumn() != null ? userSearchValues.getSortColumn() : null;
//        String sortDirection = userSearchValues.getSortDirection() != null ? userSearchValues.getSortDirection() : null;
//
//        Integer pageNumber = userSearchValues.getPageNumber() != null ? userSearchValues.getPageNumber() : null;
//        Integer pageSize = userSearchValues.getPageSize() != null ? userSearchValues.getPageSize() : null;
//
//        // направление сортировки
//        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
//
//        /* Вторым полем для сортировки добавляем id, чтобы всегда сохранялся строгий порядок.
//            Например, если у 2-х задач одинаковое значение приоритета и мы сортируем по этому полю.
//            Порядок следования этих 2-х записей после выполнения запроса может каждый раз меняться, т.к. не указано второе поле сортировки.
//            Поэтому и используем ID - тогда все записи с одинаковым значением приоритета будут следовать в одном порядке по ID.
//         */
//
//        // объект сортировки, который содержит стобец и направление
//        Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);
//
//        // объект постраничности
//        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
//
//        // результат запроса с постраничным выводом
//        Page<User> result = userService.findByParams(email, username, pageRequest);
//
//         результат запроса
//        return ResponseEntity.ok(keycloakUtils.searchKeycloakUsers(email));
//
//    }


    private UserDTO convertToUserDTO(UserRepresentation userRepresentation) {
        UserDTO userDTO = modelMapper.map(userRepresentation, UserDTO.class);
        return addConvertToUserDTO(userRepresentation, userDTO);
    }

    public UserDTO addConvertToUserDTO(UserRepresentation userRepresentation, UserDTO existingUserDTO) {
        Map<String, List<String>> attributes = userRepresentation.getAttributes();
        if (attributes != null && !attributes.isEmpty()) {
            modelMapper.map(attributes, existingUserDTO);
        }
        return existingUserDTO;
    }
}