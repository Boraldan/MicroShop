package boraldan.entitymicro.order.entity;


import boraldan.entitymicro.cart.entity.Cart;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discount")
    private double discount;

    @Column(name = "valid")
    private boolean valid;

    @Column(name = "creat_at")
    @Timestamp
    private LocalDateTime creatAt;

    @OneToMany(mappedBy = "coupon")
    private List<Order> orders;




}
