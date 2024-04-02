package boraldan.entitymicro.shop.entity.item.transport.bike;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bike")
public class Bike extends Item {

    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private BikePrice price;

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

    public Bike() {
        this.clazz = Bike.class;
        this.storageClazz = BikeStorage.class;
    }

    public void setPrice(BikePrice price) {
        this.price = price;
        price.setItem(this);
    }

    @Override
    public String getDescription() {
        return "Описание товара ....";
    }

    @Override
    public <T extends Item> T getThisItem(Class<T> clazz) {
        return null;
    }
}
