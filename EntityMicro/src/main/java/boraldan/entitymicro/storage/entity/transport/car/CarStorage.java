package boraldan.entitymicro.storage.entity.transport.car;

import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "car_storage")
public class CarStorage extends Storage {


}
