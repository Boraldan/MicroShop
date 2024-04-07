package boraldan.shop.service;

import boraldan.shop.repository.PriceUnifiedRepo;
import boraldan.shop.service.i_service.PriceUnifiedService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class PriceUnifiedServiceV1<T>  implements PriceUnifiedService<T, PriceUnifiedRepo<T>> {


    private final PriceUnifiedRepo<T> priceRepo;
    @Override
    public PriceUnifiedRepo<T> getPriceRepo() {
        return priceRepo;
    }

    @Override
    public T getById(UUID id) {
        return PriceUnifiedService.super.getById(id);
    }

    @Override
    @Transactional
    public T save(T price) {
        return PriceUnifiedService.super.save(price);
    }

    @Override
    public void delete(T price) {
        PriceUnifiedService.super.delete(price);
    }
}
