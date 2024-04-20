package boraldan.entitymicro.account.entity.person;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    protected UUID id;

    @Column(name = "username")
    protected String username;

    @Column(name = "fio")
    protected String fio;

    @Column(name = "age")
    protected int age;

    @Column(name = "phone")
    protected Long phone;

    @NotEmpty(message = "Не должно быть пустым")
    @Email
    @Column(name = "email")
    protected String email;

    @Column(name = "card")
    protected Long card;

//    @Column(name = "role")
//    protected String role;

//    @OneToOne(mappedBy = "person", targetEntity = PersonPassword.class)
//    protected Password password;

    public String info() {
        return "%s : %s : %s".formatted(id, username, email);
    }

}
