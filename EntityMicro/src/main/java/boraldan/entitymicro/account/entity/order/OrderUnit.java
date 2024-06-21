package boraldan.entitymicro.account.entity.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_unit")
public class OrderUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "item_id")
    private String itemId;

    @JoinColumn(name = "item_title")   // чтобы не подкачивать всю информацию об объекте
    private String itemTitle;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_item")
    private BigDecimal priceItem;

    @Column(name = "price_unit")
    private BigDecimal priceUnit;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // убирает зацикливание в JSON
    private Order order;


    public BigDecimal getPriceUnit() {
        if (priceItem == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return priceUnit;
    }

    @Override
    public String toString() {
        return "UnitOrder{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", quantity=" + quantity +
                ", priceItem=" + priceItem +
                ", priceUnit=" + priceUnit +
                '}';
    }
}
