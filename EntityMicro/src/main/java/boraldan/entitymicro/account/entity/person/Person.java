package boraldan.entitymicro.account.entity.person;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;



    @Column(name = "name")
    protected String name;

    @Column(name = "age")
    protected int age;

    @NotNull
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
        return "%d : %s : %s".formatted(id, name, email);
    }

}
