package boraldan.entitymicro.storage.entity;

import boraldan.entitymicro.global.category.CategoryName;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
//@Entity
//@Table(name = "storage")
@MappedSuperclass
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

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
