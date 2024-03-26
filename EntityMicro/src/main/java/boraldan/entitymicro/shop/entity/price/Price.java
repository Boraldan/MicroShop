package boraldan.entitymicro.shop.entity.price;

import boraldan.entitymicro.global.category.Category;
import boraldan.entitymicro.shop.entity.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.awt.geom.Arc2D;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "price")
public class Price {

//    @GeneratedValue(strategy = GenerationType.AUTO) // для strategy = InheritanceType.TABLE_PER_CLASS)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "base_price")
    protected BigDecimal basePrice;

    @Column(name = "coefficient")
    protected Double coefficient;

    @Column(name = "custom_price")
    protected BigDecimal customPrice;

    public BigDecimal getCustomPrice() {
        if (coefficient != null) {
            customPrice = customPrice.multiply(BigDecimal.valueOf(coefficient));
        }
        return customPrice;
    }
}
