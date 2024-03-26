package boraldan.entitymicro.shop.entity.price;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_price")
public class CarPrice extends Price {

    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @OneToOne
    private Car car;

    @Override
    public String toString() {
        return "CarPrice{" +
                "id=" + id +
                ", basePrice=" + basePrice +
                '}';
    }
}
