package boraldan.entitymicro.cart.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<UnitCart> unitCart;

    @JoinColumn(name = "customer_id")
    private Long customerId;

    @JoinColumn(name = "coupon_id")
    private Long couponId;

    @Column(name = "sub_total")
    BigDecimal subTotal = getSubTotalPrice();

    @Column(name = "total")
    BigDecimal total;

    @Column(name = "creat_at")
    @Timestamp
    private LocalDateTime creatAt;

    public Cart() {
        unitCart = new ArrayList<>();
    }

    private BigDecimal getSubTotalPrice() {
        BigDecimal subTotal = new BigDecimal(0);
        if (unitCart != null) {
            for (UnitCart item : unitCart) {
                subTotal = subTotal.add(item.getPrice());
            }
        }
        return subTotal;
    }
//
//    private BigDecimal getPriceTotal() {
//        if (coupon != null) {
//            return subTotal = subTotal.subtract(subTotal.multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
//        }
//        return subTotal;
//    }

    public int itemsInCart() {
        return unitCart.stream().mapToInt(UnitCart::getQuantity).sum();
    }
}
