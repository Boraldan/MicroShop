package boraldan.entitymicro.oauth2.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @OneToOne(targetEntity = Password.class)
    protected Password password;

    @NotNull
    @Column(name = "phone")
    protected Long phone;

    @NotEmpty(message = "Не должно быть пустым")
    @Email
    @Column(name = "email")
    protected String email;

    @Column(name = "card")
    protected Long card;

    @Column(name = "role")
    protected String role;

    abstract String info();

}
