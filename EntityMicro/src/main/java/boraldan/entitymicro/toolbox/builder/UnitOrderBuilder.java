package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.account.entity.order.OrderUnit;

import java.math.BigDecimal;
import java.util.UUID;

public class UnitOrderBuilder {
    private final OrderUnit orderUnit;

    private UnitOrderBuilder() {
        this.orderUnit = new OrderUnit();
    }

    public static UnitOrderBuilder create() {
        return new UnitOrderBuilder();
    }

    public UnitOrderBuilder setId(UUID id) {
        this.orderUnit.setId(id);
        return this;
    }

    public UnitOrderBuilder setItemId(String itemId) {
        this.orderUnit.setItemId(itemId);
        return this;
    }

    public UnitOrderBuilder setItemTitle(String itemTitle) {
        this.orderUnit.setItemTitle(itemTitle);
        return this;
    }

    public UnitOrderBuilder setQuantity(int quantity) {
        this.orderUnit.setQuantity(quantity);
        return this;
    }

    public UnitOrderBuilder setPriceItem(BigDecimal priceItem) {
        this.orderUnit.setPriceItem(priceItem);
        return this;
    }

    public UnitOrderBuilder setPriceUnit(BigDecimal priceUnit) {
        this.orderUnit.setPriceUnit(priceUnit);
        return this;
    }

    public UnitOrderBuilder setOrder(Order order) {
        this.orderUnit.setOrder(order);
        return this;
    }


    public OrderUnit build() {
        return this.orderUnit;
    }
}
