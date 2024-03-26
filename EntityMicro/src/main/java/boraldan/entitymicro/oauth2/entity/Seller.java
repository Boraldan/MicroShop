package boraldan.entitymicro.oauth2.entity;

import boraldan.entitymicro.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seller")
public class Seller extends Person {

    @Column(name = "company")
    private String company;

    @Transient
    private List<Order> orders;

    @Override
    String info() {
        return company;
    }
}
