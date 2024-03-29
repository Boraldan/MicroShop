package boraldan.entitymicro.storage.entity.transport.car;

import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "car_storage")
public class CarStorage extends Storage {


}
