package boraldan.entitymicro.storage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
//@NoArgsConstructor
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "t_storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;


    @Column(name = "item_id")
    protected UUID itemId;

    @Column(name = "quantity")
    protected long quantity;

    @Column(name = "reserve")
    protected long reserve;

    @Transient
    protected Class<? extends Storage> storageClazz;

    public Storage() {
        this.storageClazz = this.getClass();
    }


    //
//    public <T extends Storage> T getThisStorage(Class<T> clazz) {
//        return clazz.cast(this);
//    }


    @Override
    public String toString() {
        return "Storage{" +
                "quantity=" + quantity +
                ", reserve=" + reserve +
                '}';
    }
}
