package boraldan.entitymicro.oauth2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "password")
    private Person person;

    @NotEmpty(message = "Не должно быть пустым")
    @Column(name = "password")
    private String password;
}
