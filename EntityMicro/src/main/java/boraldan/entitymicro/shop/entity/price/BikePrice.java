package boraldan.entitymicro.shop.entity.price;

import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bike_price")
public class BikePrice extends Price{

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonBackReference
    @MapsId  // или так @JoinColumn(name = "bike_id", referencedColumnName = "id")
    @OneToOne
    private Bike bike;
}
