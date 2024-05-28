package boraldan.entitymicro.shop.entity.item.transport.bike;


import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bike")
public class Bike extends Item {

//    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private BikePrice price;

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
        this.itemClazz = Bike.class;
        this.price = new BikePrice();
        this.storageClazz = BikeStorage.class;
        this.category = new Category();
        category.setCategoryName(CategoryName.BIKE);
        this.priceClazz = BikePrice.class;
    }

    public void initItemTitle() {
        String itemTitle = "%s %s %s %s %s".formatted(
                Objects.requireNonNullElse(name, ""),
                Objects.requireNonNullElse(factory, ""),
                Objects.requireNonNullElse(wheels, ""),
                Objects.requireNonNullElse(year, ""),
                Objects.requireNonNullElse(fuel, "")
        );
        super.setTitle(itemTitle);
    }

    public void setPrice(BikePrice price) {
        this.price = price;
        price.setItem(this);
    }



}
