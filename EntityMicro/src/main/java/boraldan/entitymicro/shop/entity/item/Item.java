package boraldan.entitymicro.shop.entity.item;

import boraldan.entitymicro.shop.entity.category.Category;
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
public class Item {

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


    public <T extends Item> T getThisItem(Class<T> clazz) {
        return clazz.cast(this);
    }

}
