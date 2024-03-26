package boraldan.entitymicro.storage.entity.transport.car_relate.wheel;



import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "car_wheel_storage")
public class CarWheelStorage  extends Storage {

//    @Column(name = "car_wheel_id")
//    private Long carWheelId;
//
//    private long quantity;
//    private long reserve;
}
