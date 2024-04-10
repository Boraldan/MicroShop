package boraldan.entitymicro.account.entity.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_order")
public class UnitOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "item_id")
    private Long itemId;

    @JoinColumn(name = "item_title")   // чтобы не подкачивать всю информацию об объекте
    private String itemTitle;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_item")
    private BigDecimal priceItem;

    @Column(name = "price")
    private BigDecimal priceUnit;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;


    public BigDecimal getPriceUnit() {
        if (priceItem != null) {
            return priceItem.multiply(BigDecimal.valueOf(quantity));
        } else {
            return BigDecimal.ZERO;
        }
    }
}
