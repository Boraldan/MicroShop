package boraldan.entitymicro.shop.entity.item.transport.bike_relate;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.price.item_price.BikeWheelPrice;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    /**
     * Устанавливаем двухсторонию связь между BikeWheelPrice и Item для сохранения в db
     *
     * @param price цена на товар
     */
    public void setPrice(BikeWheelPrice price) {
        this.price = price;
        price.setItem(this);
    }


}
