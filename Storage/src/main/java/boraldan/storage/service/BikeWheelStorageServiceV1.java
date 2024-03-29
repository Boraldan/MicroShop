package boraldan.storage.service;

import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.entitymicro.storage.entity.transport.bike_relate.wheel.BikeWheelStorage;
import boraldan.storage.repository.BikeWheelStorageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BikeWheelStorageServiceV1   {

    private final BikeWheelStorageRepo bikeWheelStorageRepo;
    public BikeWheelStorage getByItemId(Long itemId) {
        return bikeWheelStorageRepo.findByItemId(itemId).orElse(null);
    }


    public BikeWheelStorage saveNew(StorageDto storageDto) {
        return null;
    }
}
