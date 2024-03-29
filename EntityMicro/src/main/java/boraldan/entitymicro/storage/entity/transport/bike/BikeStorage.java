package boraldan.entitymicro.storage.entity.transport.bike;


import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bike_storage")
public class BikeStorage extends Storage {
}