package boraldan.entitymicro.account.entity.person;

import boraldan.entitymicro.account.entity.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends Person {

//    @Column(name = "cart_id")
//    private UUID cartId;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // убирает зацикливание в JSON
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Customer{" +
                "orders=" + orders +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", card=" + card +
                '}';
    }
}
