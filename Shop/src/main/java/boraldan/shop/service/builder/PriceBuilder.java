package boraldan.shop.service.builder;

import boraldan.entitymicro.shop.entity.price.Price;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class PriceBuilder {

    private final Price price;
    private final Class<?> clazz;

    public PriceBuilder(Class<?> clazz) {
        this.price = new Price();
        this.clazz = clazz;
    }

    public PriceBuilder setBasePrice(double basePrice) {
        price.setBasePrice(BigDecimal.valueOf(basePrice));
        return this;
    }

    public PriceBuilder setCoefficient(double coefficient) {
        price.setCoefficient(coefficient);
        return this;
    }

    @SneakyThrows
    public <T extends Price> T builder() {
        return convertTo(price.clone(), clazz);
    }

    private <T extends Price> T convertTo(Price price, Class<?> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return new ModelMapper().map(price, targetType);
    }

}
