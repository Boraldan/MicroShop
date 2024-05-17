package boraldan.entitymicro.account.entity;


import boraldan.entitymicro.account.entity.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "discount")
    private double discount;

    @Column(name = "valid")
    private boolean valid;

    @JsonIgnore
    @Timestamp
    @Column(name = "creat_at")
    private LocalDateTime creatAt;

    @OneToMany(mappedBy = "coupon")
    private List<Order> orders;




}
