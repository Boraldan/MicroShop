package boraldan.entitymicro.account.entity.person;

import boraldan.entitymicro.account.entity.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends Person {

    @Column(name = "cart_id")
    private UUID cartId;

    @OneToMany (mappedBy = "customer")
    private List<Order> orders;



    @Override
    public String toString() {
        return "Customer{" +
                "cartId=" + cartId +
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
