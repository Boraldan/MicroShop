package boraldan.entitymicro.account.entity.order;

import boraldan.entitymicro.account.entity.Coupon;
import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.account.entity.seller.Seller;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // убирает зацикливание в JSON
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // убирает зацикливание в JSON
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderUnit> items;

    @ManyToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    public BigDecimal subTotalPrice() {
        BigDecimal subTotal = BigDecimal.ZERO;
        if (items != null) {
            for (OrderUnit orderUnit : items) {
                subTotal = subTotal.add(orderUnit.getPriceUnit().multiply(BigDecimal.valueOf(orderUnit.getQuantity())));
            }
        }
        return subTotal;
    }

    public BigDecimal totalPrice() {
        if (coupon != null) {
            return subTotal = subTotal.subtract(subTotal.multiply(BigDecimal.valueOf(coupon.getDiscount() / 100)));
        }
        return subTotal;
    }

    public void initPrices() {
        this.subTotal = subTotalPrice();
        this.total = totalPrice();
    }

    public String orderInfo() {
        return "Заказ №%s от %d.%d.%d\nСтатус заказа: %s\nСтатус оплаты: %s" .formatted(id, creatAt.getDayOfMonth(),
                creatAt.getMonthValue(), creatAt.getYear(), orderStatus.name(), pay.name());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", subTotal=" + subTotal +
                ", total=" + total +
                ", orderStatus=" + orderStatus +
                ", pay=" + pay +
                ", creatAt=" + creatAt +
                ", items=" + items +
                ", coupon=" + coupon +
                '}';
    }
}
