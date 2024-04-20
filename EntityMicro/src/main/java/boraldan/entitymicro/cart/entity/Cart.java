package boraldan.entitymicro.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Transient
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<UnitCart> unitCart;

    @JoinColumn(name = "customer_id")
    private UUID customerId;

    @JoinColumn(name = "coupon_id")
    private UUID couponId;

    @Column(name = "sub_total")
    BigDecimal subTotal;

    @Column(name = "total")
    BigDecimal total;

    @JsonIgnore
    @Column(name = "creat_at")
    @Timestamp
    private LocalDateTime creatAt;

    public Cart() {
        unitCart = new ArrayList<>();
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal(0);
        if (unitCart != null) {
            for (UnitCart item : unitCart) {
                subTotal = subTotal.add(item.getPrice());
            }
        }
        return subTotal;
    }

//    private BigDecimal getPriceTotal() {
//        if (coupon != null) {
//            return subTotal = subTotal.subtract(subTotal.multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
//        }
//        return subTotal;
//    }

    public int itemsInCart() {
        return unitCart.stream().mapToInt(UnitCart::getQuantity).sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
