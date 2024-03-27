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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    protected Category category;

    public <T extends Item> T getThisItem(Class<T> clazz) {
        return clazz.cast(this);
    }

}
