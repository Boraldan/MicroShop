package boraldan.entitymicro.shop.entity.price;

import boraldan.entitymicro.shop.entity.item.Item;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "price")
public class Price  {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) // для strategy = InheritanceType.TABLE_PER_CLASS)
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

//    @Expose(serialize = false) //gson аннотация
    //    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

//    @Override
//    public Price clone() throws CloneNotSupportedException {
//        return (Price) super.clone();
//    }


    @Override
    public String toString() {
        return "Price{" +
                "customPrice=" + customPrice +
                '}';
    }
}
