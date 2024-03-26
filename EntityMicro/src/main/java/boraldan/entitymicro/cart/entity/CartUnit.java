package boraldan.entitymicro.cart.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_unit")
public class CartUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_item")
    private BigDecimal priceItem ;

    @Column(name = "price")
    private BigDecimal price;


//    private BigDecimal priceItem(){
//        return item != null ? item.getPrice() : new BigDecimal(0);
//    }
//
//    private BigDecimal price(){
//        return priceItem.multiply(BigDecimal.valueOf(quantity));
//    }

}
