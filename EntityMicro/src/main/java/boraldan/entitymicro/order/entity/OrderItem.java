package boraldan.entitymicro.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_item")
    private BigDecimal priceItem;

    @Column(name = "price")
    private BigDecimal price = price();

//    private BigDecimal priceItem(){
//        return item != null ? item.getPrice() : new BigDecimal(0);
//    }

    private BigDecimal price(){
        if (priceItem != null) {
            return priceItem.multiply(BigDecimal.valueOf(quantity));
        } else {
            return BigDecimal.ZERO;
        }
    }

}
