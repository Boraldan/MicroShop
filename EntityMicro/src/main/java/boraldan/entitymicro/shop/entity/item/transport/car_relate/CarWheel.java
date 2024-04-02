package boraldan.entitymicro.shop.entity.item.transport.car_relate;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.item_price.CarWheelPrice;
import boraldan.entitymicro.storage.entity.transport.car_relate.wheel.CarWheelStorage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "car_wheel")
public class CarWheel extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "diameter")
    private double diameter;

    @Column(name = "season")
    private String season;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private CarWheelPrice price;

    public CarWheel() {
        this.clazz = CarWheel.class;
        this.storageClazz = CarWheelStorage.class;
    }

    //Устанавливаем двухсторонию связь между CarWheelPrice и Item для сохранения в db
    public void setPrice(CarWheelPrice price) {
        this.price = price;
        price.setItem(this);
    }

    @Override
    public String getDescription() {
        return "Описание товара ....";
    }
}
