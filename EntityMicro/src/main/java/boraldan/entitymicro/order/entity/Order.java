package boraldan.entitymicro.order.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @JoinColumn(name = "customer_id")
    private Long customerId;

    @JoinColumn(name = "seller_id")
    private Long sellerId;

    @ManyToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @Column(name = "sub_total")
    BigDecimal subTotal = getSubTotalPrice();

    @Column(name = "total")
    BigDecimal total = getPriceTotal();

    @Column(name = "creat_at")
    @Timestamp
    private LocalDateTime creatAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "pay")
    @Enumerated(EnumType.STRING)
    private OrderPay pay;

    private BigDecimal getSubTotalPrice() {
        BigDecimal subTotal = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItem orderItem : items) {
                subTotal = subTotal.add(orderItem.getPrice());
            }
        }
        return subTotal;
    }

    private BigDecimal getPriceTotal() {
        if (coupon != null) {
            return subTotal = subTotal.subtract(subTotal.multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
        }
        return subTotal;
    }

    public String orderInfo() {
        return "Заказ №%d от %d.%d.%d\nСтатус заказа: %s\nСтатус оплаты: %s".formatted(id, creatAt.getDayOfMonth(),
                creatAt.getMonthValue(), creatAt.getYear(), status.name(), pay.name());
    }
}
