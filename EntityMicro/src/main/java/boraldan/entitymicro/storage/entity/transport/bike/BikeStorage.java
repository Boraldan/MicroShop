package boraldan.entitymicro.storage.entity.transport.bike;


import boraldan.entitymicro.storage.entity.Storage;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bike_storage")
public class BikeStorage extends Storage {
}