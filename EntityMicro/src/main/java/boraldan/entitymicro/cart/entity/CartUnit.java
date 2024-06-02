package boraldan.entitymicro.cart.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_unit")
public class CartUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "quantity")
    private Integer quantity;

//    @Column(name = "price_item")
//    private BigDecimal priceItem ;
//
//    @Column(name = "price")
//    private BigDecimal price;


}
