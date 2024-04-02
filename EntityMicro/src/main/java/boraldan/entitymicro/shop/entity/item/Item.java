package boraldan.entitymicro.shop.entity.item;

import boraldan.entitymicro.shop.entity.category.Category;
import boraldan.entitymicro.shop.entity.price.Price;
import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_item")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

//    варианты сериализации JSON
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonManagedReference
//    @JsonBackReference
//    @JsonView(Category.class)

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    protected Category category;


    @Column(name = "storage_id")
    protected UUID storageId;

    @Transient
    protected Storage storage;

    @Transient
    protected Class<?> clazz;

    @Transient
    protected Class<?> storageClazz;

    public abstract Price getPrice();

//    public abstract Class<?> getClazz();
//
//    public abstract void setClazz(Class<?> clazz);
//
//    public abstract Class<?> getStorageClazz();
//
//    public abstract void setStorageClazz(Class<?> storageClazz);




    

    public abstract String getName();

    public abstract String getDescription();

    public <T extends Item> T getThisItem(Class<T> clazz) {
        return clazz.cast(this);
    }


}
