package boraldan.entitymicro.shop.entity.price;

import lombok.SneakyThrows;

import java.math.BigDecimal;

public class PriceBuilder<T extends Price> {

    private final T price;

    public PriceBuilder(T price) {
        this.price = price;
    }

    public PriceBuilder<T> setBasePrice(double basePrice) {
        price.setBasePrice(BigDecimal.valueOf(basePrice));
        return this;
    }

    public PriceBuilder<T> setCoefficient(double coefficient) {
        price.setCoefficient(coefficient);
        return this;
    }

    @SneakyThrows
    public T builder() {
        this.price.initCustomPrice();
        return (T) price.clone();
    }

}
