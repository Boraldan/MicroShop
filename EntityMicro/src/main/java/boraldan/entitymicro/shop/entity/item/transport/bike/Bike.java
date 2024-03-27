package boraldan.entitymicro.shop.entity.item.transport.bike;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bike")
public class Bike extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "factory")
    private String factory;

    @Column(name = "wheels")
    private Integer wheels;

    @Column(name = "year_prod")
    private Integer year;

    @Column(name = "fuel", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private BikePrice price;

    public void setPrice(BikePrice price) {
        this.price = price;
        price.setItem(this);
    }

}
