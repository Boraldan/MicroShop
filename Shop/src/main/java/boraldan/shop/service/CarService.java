package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> getById(Long id);
    List<Car> getAll();
    Car save(Car car);

    void dellById(Long id);
}
