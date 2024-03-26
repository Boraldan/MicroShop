package boraldan.entitymicro.shop.entity.item;

import boraldan.entitymicro.shop.entity.category.Category;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    protected Category category;

//    @Column(name = "storage_id")
//    protected Long storageId;

    public <T extends Item> T getThisItem(Class<T> clazz) {
        return clazz.cast(this);
    }

}
