package boraldan.shop.config;

import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.entitymicro.shop.entity.price.item_price.BikeWheelPrice;
import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.shop.entity.price.item_price.CarWheelPrice;
import boraldan.shop.repository.PriceUnifiedRepo;
import boraldan.shop.service.i_service.PriceUnifiedService;
import boraldan.shop.service.PriceUnifiedServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceServiceConfig {

    @Bean
    public PriceUnifiedService<CarPrice, PriceUnifiedRepo<CarPrice>> carPriceService(
            PriceUnifiedRepo<CarPrice> priceJpaRepo) {
        return new PriceUnifiedServiceV1<>(priceJpaRepo);
    }

    @Bean
    public PriceUnifiedService<CarWheelPrice, PriceUnifiedRepo<CarWheelPrice>> carWheelPriceService(
            PriceUnifiedRepo<CarWheelPrice> priceJpaRepo) {
        return new PriceUnifiedServiceV1<>(priceJpaRepo);
    }

    @Bean
    public PriceUnifiedService<BikePrice, PriceUnifiedRepo<BikePrice>> bikePriceService(
            PriceUnifiedRepo<BikePrice> priceJpaRepo) {
        return new PriceUnifiedServiceV1<>(priceJpaRepo);
    }

    @Bean
    public PriceUnifiedService<BikeWheelPrice, PriceUnifiedRepo<BikeWheelPrice>> bikeWheelPriceService(
            PriceUnifiedRepo<BikeWheelPrice> priceJpaRepo) {
        return new PriceUnifiedServiceV1<>(priceJpaRepo);
    }


}
