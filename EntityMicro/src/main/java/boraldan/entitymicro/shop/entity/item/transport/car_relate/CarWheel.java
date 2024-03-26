package boraldan.entitymicro.shop.entity.item.transport.car_relate;


import boraldan.entitymicro.shop.entity.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @Column(name = "price")
    private BigDecimal price;

//    @ElementCollection
//    private List<String> images;

    public String itemTitle() {
        return id + name;
    }
}
