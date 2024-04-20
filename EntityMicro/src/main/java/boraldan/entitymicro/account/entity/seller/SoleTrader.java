package boraldan.entitymicro.account.entity.seller;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sole_trader")
public class SoleTrader extends Seller{

    @Column(name = "sole_name")
    private String soleName;

    @Override
    public String info() {
        return "%s : %s : %s".formatted(id, soleName, email);
    }
}
