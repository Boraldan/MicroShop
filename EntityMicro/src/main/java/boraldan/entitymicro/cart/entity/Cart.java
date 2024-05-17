package boraldan.entitymicro.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

//    @Transient
//    private String ownerName;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartUnit> cartUnitList;

    @JoinColumn(name = "customer_id")
    private UUID customerId;

    @Column(name = "coupon_name")
    private String couponName;

//    @Column(name = "sub_total")
//    private BigDecimal subTotal;
//
//    @Column(name = "total")
//    private BigDecimal total;

    @JsonIgnore
    @Column(name = "creat_at")
    @Timestamp
    private LocalDateTime creatAt;

    public Cart() {
        this.cartUnitList = new ArrayList<>();
        this.creatAt = LocalDateTime.now();
    }
//
//    public BigDecimal subTotal() {
//        BigDecimal subTotal = new BigDecimal(0);
//        if (unitCart != null) {
//            for (UnitCart item : unitCart) {
//                subTotal = subTotal.add(item.getPrice());
//            }
//        }
//        return subTotal;
//    }
//
////    private BigDecimal getPriceTotal() {
////        if (coupon != null) {
////            return subTotal = subTotal.subtract(subTotal.multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
////        }
////        return subTotal;
////    }
//
//    public int lotInCart() {
//        return unitCart != null ? unitCart.stream().mapToInt(UnitCart::getQuantity).sum() : 0;
//    }

    @Override
    public String toString() {
        return "Cart{" +
//                ", name='" + ownerName + '\'' +
                ", id=" + id +
                '}';
    }
}
