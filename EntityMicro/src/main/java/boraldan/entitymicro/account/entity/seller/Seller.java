package boraldan.entitymicro.account.entity.seller;

import boraldan.entitymicro.account.entity.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Seller")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected UUID id;

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
//
//    @OneToOne(mappedBy = "seller", targetEntity = SellerPassword.class)
//    protected Password password;

    @OneToMany(mappedBy = "seller")
    protected List<Order> orders;

    public String info() {
        return "%d : %s".formatted(id, email);
    }

}
