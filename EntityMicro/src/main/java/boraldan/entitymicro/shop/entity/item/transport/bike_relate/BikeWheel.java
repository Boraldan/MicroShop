package boraldan.entitymicro.shop.entity.item.transport.bike_relate;


import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.item_price.BikeWheelPrice;
import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    private double diameter;

    @Column(name = "season")
    private String season;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private BikeWheelPrice price;

    public BikeWheel() {
        this.itemClazz = BikeWheel.class;
        this.storageClazz = BikeWheelStorage.class;
        this.category = new Category();
        category.setCategoryName(CategoryName.BIKE_WHEEL);
        this.priceClazz = BikeWheelPrice.class;
    }

   // Устанавливаем двухсторонию связь между BikeWheelPrice и Item для сохранения в db
     public void setPrice(BikeWheelPrice price) {
        this.price = price;
        price.setItem(this);
    }


    @Override
    public String getDescription() {
        return "Описание товара ....";
    }

}
