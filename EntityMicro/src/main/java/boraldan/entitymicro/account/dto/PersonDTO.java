package boraldan.entitymicro.account.dto;

import lombok.Data;

import java.util.UUID;

// пользователь - основной объект, с которым связаны все остальные (через внешние ключи)
@Data
public class PersonDTO {
    private String id;
    private String username;
    private String fio;
    private int age;
    private Long phone;
    private String email;
    private Long card;
    private String role;
    private String password;
    private UUID cartId;
//    private List<Order> orders;

}
