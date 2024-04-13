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
@Table(name = "Customer")
public class Customer extends Person {

    @Column(name = "cart_id")
    private UUID cartId;

    @OneToMany (mappedBy = "customer")
    private List<Order> orders;

    @Override
    public String info() {
        return "%d : %s : %s".formatted(id, name, email );
    }

}
