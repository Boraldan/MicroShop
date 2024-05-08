package boraldan.entitymicro.shop.entity.item.transport.car_relate;


import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.item_price.CarWheelPrice;
import boraldan.entitymicro.storage.entity.transport.car_relate.wheel.CarWheelStorage;
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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "car_wheel")
public class CarWheel extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "diameter")
    private Double diameter;

    @Column(name = "season")
    private String season;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    private CarWheelPrice price;

    public CarWheel() {
        this.price = new CarWheelPrice();
        this.itemClazz = CarWheel.class;
        this.storageClazz = CarWheelStorage.class;
        this.category = new Category();
        category.setCategoryName(CategoryName.CAR_WHEEL);
        this.priceClazz = CarWheelPrice.class;
    }

    public void initItemTitle() {
        String itemTitle = "%s %s %s".formatted(
                Objects.requireNonNullElse(name, ""),
                Objects.requireNonNullElse(diameter, ""),
                Objects.requireNonNullElse(season, "")
        );
        super.setTitle(itemTitle);
    }

    //Устанавливаем двухсторонию связь между CarWheelPrice и Item для сохранения в db
    public void setPrice(CarWheelPrice price) {
        this.price = price;
        price.setItem(this);
    }


    public String getDescription() {
        return "Описание товара ....";
    }
}
