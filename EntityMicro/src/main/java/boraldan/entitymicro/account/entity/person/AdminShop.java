package boraldan.entitymicro.account.entity.person;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin_shop")
public class AdminShop extends Person{

    @Override
    public String info() {
        return "%d : %s : %s".formatted(id, name, role);
    }
}
