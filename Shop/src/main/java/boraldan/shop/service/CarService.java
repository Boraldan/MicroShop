package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.shop.repository.CarRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {

    private final CarRepo carRepo;

    @Override
    public Optional<Car> getById(Long id) {
        return carRepo.findById(id);
    }

    public List<Car> getAll() {
        return carRepo.findAll();
    }

    public Car save(Car car) {
        return carRepo.save(car);
    }
}
