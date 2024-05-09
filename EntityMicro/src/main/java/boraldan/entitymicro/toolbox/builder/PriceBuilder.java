package boraldan.entitymicro.toolbox.builder;

import boraldan.entitymicro.shop.entity.price.Price;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class PriceBuilder {

    private final Price price;
    private final Class<? extends Price> clazz;

    private PriceBuilder(Class<? extends Price> clazz) {
        this.price = new Price();
        this.clazz = clazz;
    }

    public static PriceBuilder creat(Class<? extends Price> clazz) {
        return new PriceBuilder(clazz);
    }

    public PriceBuilder setBasePrice(double basePrice) {
        price.setBasePrice(BigDecimal.valueOf(basePrice));
        return this;
    }

    public PriceBuilder setCoefficient(double coefficient) {
        price.setCoefficient(coefficient);
        return this;
    }

    //    @SneakyThrows
    public <T extends Price> T build() {
//        return convertTo(price.clone(), clazz);
        return convertTo(price, clazz);
    }

    private <T extends Price> T convertTo(Price price, Class<? extends Price> clazz) {
        Type targetType = TypeFactory.rawClass(clazz);
        return new ModelMapper().map(price, targetType);
    }

}
