package boraldan.entitymicro.account.entity.order;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.account.entity.seller.Seller;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "total")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", columnDefinition = "VARCHAR")
    private OrderStatus orderStatus;

    @Column(name = "pay", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private OrderPay pay;

    @Timestamp
    @Column(name = "creat_at")
    private LocalDateTime creatAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @OneToMany(mappedBy = "order")
    private List<UnitOrder> items;

    @ManyToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = BigDecimal.ZERO;
        if (items != null) {
            for (UnitOrder unitOrder : items) {
                subTotal = subTotal.add(unitOrder.getPriceUnit());
            }
        }
        return subTotal;
    }

    public BigDecimal getTotal() {
        if (coupon != null) {
            return subTotal = subTotal.subtract(subTotal.multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
        }
        return subTotal;
    }

    public String orderInfo() {
        return "Заказ №%d от %d.%d.%d\nСтатус заказа: %s\nСтатус оплаты: %s".formatted(id, creatAt.getDayOfMonth(),
                creatAt.getMonthValue(), creatAt.getYear(), orderStatus.name(), pay.name());
    }
}
