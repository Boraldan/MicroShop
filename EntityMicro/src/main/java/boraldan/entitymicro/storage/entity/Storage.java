package boraldan.entitymicro.storage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "t_storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

//    @Column(name = "category_name", columnDefinition = "VARCHAR")
//    @Enumerated(EnumType.STRING)
//    private CategoryName categoryName;

    @Column(name = "item_id")
    protected Long itemId;

    @Column(name = "quantity")
    protected long quantity;

    @Column(name = "reserve")
    protected long reserve;

//
//    public <T extends Storage> T getThisStorage(Class<T> clazz) {
//        return clazz.cast(this);
//    }

}
