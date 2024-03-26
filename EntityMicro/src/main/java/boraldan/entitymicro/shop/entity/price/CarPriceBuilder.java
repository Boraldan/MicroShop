package boraldan.entitymicro.shop.entity.price;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class CarPriceBuilder {

    private final CarPrice carPrice;

    public CarPriceBuilder() {
        this.carPrice = new CarPrice();
    }

    public CarPriceBuilder setBasePrice(double basePrice) {
        carPrice.setBasePrice(BigDecimal.valueOf(basePrice));
        return this;
    }

    public CarPriceBuilder setCoefficient(double coefficient) {
        carPrice.setCoefficient(coefficient);
        return this;
    }

    public CarPrice builder() {
        this.carPrice.initCustomPrice();
        return carPrice;
    }
}
