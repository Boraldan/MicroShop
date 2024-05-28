package boraldan.entitymicro.shop.entity.item.transport.car;


import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.category.CategoryName;
import boraldan.entitymicro.shop.entity.item.Item;
import boraldan.entitymicro.shop.entity.item.transport.Fuel;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "car")
public class Car extends Item {

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    //  @Cascade(org.hibernate.annotations.CascadeType.ALL) // из библиотеки Hibernate
    //  @OneToOne(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) // из библиотеке JPA
//    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL) // из библиотеке JPA
//    private CarPrice price;

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

//    @ElementCollection
//    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
//    @Column(name = "image")
//    private List<String> images;

    public Car() {
        this.itemClazz = Car.class;
        this.priceClazz = CarPrice.class;
        this.storageClazz = CarStorage.class;
        this.price = new CarPrice();
        this.category = new Category();
        category.setCategoryName(CategoryName.CAR);
    }

    public void initItemTitle(){
        String itemTitle = "%s %s %s %s %s".formatted(
                Objects.requireNonNullElse(name, ""),
                Objects.requireNonNullElse(factory, ""),
                Objects.requireNonNullElse(year, ""),
                Objects.requireNonNullElse(types, ""),
                Objects.requireNonNullElse(fuel, "")
        );
        super.setTitle(itemTitle);
    }

    // Устанавливаем двухстороннюю связь между CarPrice и Item для сохранения в db
    public void setPrice(CarPrice price) {
        this.price = price;
        price.setItem(this);
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", storageClazz=" + storageClazz +
                ", id=" + id +
                ", category=" + category +
                ", storage=" + storage +
                '}';
    }



}
