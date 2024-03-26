package boraldan.entitymicro.shop.entity.item.transport.car;


import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.CarPrice;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "factory")
    private String factory;

    @Column(name = "year_prod")
    private Integer year;

    @Column(name = "types", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private Types types;

    @Column(name = "fuel", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    //    @JsonManagedReference
    @OneToOne(mappedBy = "car", cascade = CascadeType.REMOVE)
    private CarPrice price;

//    @ElementCollection
//    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
//    @Column(name = "image")
//    private List<String> images;

    public String itemTitle() {
        return id + name;
    }


    @Override
    public String toString() {
        return "Car{" +
                " id=" + id + '\'' +
                " name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
