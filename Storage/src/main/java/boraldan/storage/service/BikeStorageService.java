package boraldan.storage.service;

import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.entitymicro.storage.entity.transport.bike.BikeStorage;
import boraldan.storage.repository.BikeStorageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BikeStorageService {

    private final BikeStorageRepo bikeStorageRepo;

    public BikeStorage getByCarId(Long itemId) {
        return bikeStorageRepo.findByItemId(itemId).orElse(null);
    }

}
