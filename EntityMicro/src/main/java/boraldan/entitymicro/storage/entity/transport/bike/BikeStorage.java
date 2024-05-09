package boraldan.entitymicro.storage.entity.transport.bike;


import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bike_storage")
public class BikeStorage extends Storage {
}