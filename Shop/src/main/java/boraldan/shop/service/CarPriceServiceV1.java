package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.price.item_price.CarPrice;
import boraldan.entitymicro.shop.entity.price.PriceBuilder;
import boraldan.shop.repository.CarPriceRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CarPriceServiceV1 {

    private final CarPriceRepo carPriceRepo;

    @Getter
    private final PriceBuilder<CarPrice> priceBuilder = new PriceBuilder<>(new CarPrice());

    @Transactional
    public CarPrice save(CarPrice carPrice){
        return carPriceRepo.save(carPrice);
    }


}
