package boraldan.storage.service;


import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.CarStorageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarStorageService {

    private final CarStorageRepo carStorageRepo;

    public CarStorage getByCarId(Long itemId){
        return carStorageRepo.findByItemId(itemId).orElse(null);
    }



}
