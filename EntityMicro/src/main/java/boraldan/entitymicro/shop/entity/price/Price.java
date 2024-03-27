package boraldan.entitymicro.shop.entity.price;

import boraldan.entitymicro.shop.entity.item.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
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

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // для strategy = InheritanceType.TABLE_PER_CLASS)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonBackReference
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @OneToOne
    protected Item item;

    @Column(name = "base_price")
    protected BigDecimal basePrice;

    @Column(name = "coefficient")
    protected Double coefficient;

    @Column(name = "custom_price")
    protected BigDecimal customPrice;

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
        this.initCustomPrice();
    }

    protected void initCustomPrice() {
        if (coefficient != null) {
            this.customPrice = basePrice.multiply(BigDecimal.valueOf(coefficient));
        }
    }
}
