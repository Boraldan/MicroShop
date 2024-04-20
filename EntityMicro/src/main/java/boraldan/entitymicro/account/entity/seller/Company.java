package boraldan.entitymicro.account.entity.seller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "company")
public class Company extends Seller {

    @Column(name = "company_name")
    private String companyName;

    @Override
    public String info() {
        return "%s : %s : %s".formatted(id, companyName, email);
    }
}



