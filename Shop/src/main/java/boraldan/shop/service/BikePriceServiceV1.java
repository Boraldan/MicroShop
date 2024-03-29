package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.price.PriceBuilder;
import boraldan.entitymicro.shop.entity.price.item_price.BikePrice;
import boraldan.shop.repository.BikePriceRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BikePriceServiceV1 {

    private final BikePriceRepo bikePriceRepo;

    @Getter
    private final PriceBuilder<BikePrice> priceBuilder = new PriceBuilder<>(new BikePrice());

    @Transactional
    public BikePrice save(BikePrice bikePrice){
        return bikePriceRepo.save(bikePrice);
    }

}
