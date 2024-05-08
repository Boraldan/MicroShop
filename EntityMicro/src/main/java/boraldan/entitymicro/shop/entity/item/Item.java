package boraldan.entitymicro.shop.entity.item;

import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.price.Price;
import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@JsonInclude(JsonInclude.Include.NON_ABSTRACT)
//@JsonInclude(JsonInclude.Include.ALWAYS)


// мешает стандартной десириализации из Json
//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type"
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Item.class, name = "item"),
//        @JsonSubTypes.Type(value = Car.class, name = "car"),
//        @JsonSubTypes.Type(value = Bike.class, name = "bike")
//        // Добавьте сюда другие подклассы Item при необходимости
//})

//@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(name = "title")
    private String title;

//    варианты сериализация JSON
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonManagedReference
//    @JsonBackReference
//    @JsonView(Category.class)
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    protected Category category;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL) // из библиотеке JPA
    protected Price price;

    @Transient
    protected Storage storage;

    @Transient
    protected Class<? extends Item> itemClazz;

    @Transient
    protected Class<? extends Storage> storageClazz;

    @Transient
    protected Class<? extends Price> priceClazz;


    public <T extends Item> T getThisItem(Class<T> clazz) {
        return clazz.cast(this);
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", price=" + price +
//                ", storageId=" + storageId +
                ", storage=" + storage +
                ", itemClazz=" + itemClazz +
                ", storageClazz=" + storageClazz +
                ", priceClazz=" + priceClazz +
                '}';
    }

}
