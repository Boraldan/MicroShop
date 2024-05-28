package boraldan.entitymicro.shop.entity.item.transport.bike_relate;


import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.item_price.BikeWheelPrice;
import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Table(name = "bike_wheel")
public class BikeWheel extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "diameter")
    private Double diameter;

    @Column(name = "season")
    private String season;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private BikeWheelPrice price;

    public BikeWheel() {
        this.itemClazz = BikeWheel.class;
        this.price = new BikeWheelPrice();
        this.storageClazz = BikeWheelStorage.class;
        this.category = new Category();
        category.setCategoryName(CategoryName.BIKE_WHEEL);
        this.priceClazz = BikeWheelPrice.class;
    }

    public void initItemTitle() {
        String itemTitle = "%s %s %s".formatted(
                Objects.requireNonNullElse(name, ""),
                Objects.requireNonNullElse(diameter, ""),
                Objects.requireNonNullElse(season, "")
        );
        super.setTitle(itemTitle);
    }

   // Устанавливаем двухсторонию связь между BikeWheelPrice и Item для сохранения в db
     public void setPrice(BikeWheelPrice price) {
        this.price = price;
        price.setItem(this);
    }

}
