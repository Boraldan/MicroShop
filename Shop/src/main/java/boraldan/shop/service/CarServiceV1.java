package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.shop.repository.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceV1 implements CarService {

    private final CarRepo carRepo;

    @Override
    public Optional<Car> getById(Long id) {
        return carRepo.findById(id);
    }

    public List<Car> getAll() {
        return carRepo.findAll();
    }

    @Transactional
    public Car save(Car car) {
        return carRepo.save(car);
    }


    @Transactional
    public void dellById(Long id){
        carRepo.deleteById(id);
    }

}
