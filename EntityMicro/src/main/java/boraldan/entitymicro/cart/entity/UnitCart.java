package boraldan.entitymicro.cart.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_cart")
public class UnitCart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

}
