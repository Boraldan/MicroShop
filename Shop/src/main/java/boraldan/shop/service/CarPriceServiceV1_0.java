package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.price.CarPrice;
import boraldan.shop.repository.CarPriceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CarPriceServiceV1_0 {

    private final CarPriceRepo carPriceRepo;

    @Transactional
    public CarPrice save(CarPrice carPrice){
        return carPriceRepo.save(carPrice);
    }


}
