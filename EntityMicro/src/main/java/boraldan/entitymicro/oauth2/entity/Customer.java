package boraldan.entitymicro.oauth2.entity;

import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends Person {

    @Column(name = "name")
    private String name;

    private int age;

    @Column(name = "cart_id")
    private Long cartId;

    @Transient
    private List<Order> orders;

    @Override
    public String info() {
        return "%d : %s : %s".formatted(id, name, email);
    }

}
