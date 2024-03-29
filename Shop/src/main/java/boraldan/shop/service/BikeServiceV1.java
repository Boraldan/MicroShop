package boraldan.shop.service;

import boraldan.entitymicro.shop.entity.item.transport.bike.Bike;
import boraldan.shop.repository.BikeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BikeServiceV1 {
    private final BikeRepo bikeRepo;


    public Optional<Bike> getById(Long id) {
        return bikeRepo.findById(id);
    }

    public List<Bike> getAll() {
        return bikeRepo.findAll();
    }

    @Transactional
    public Bike save(Bike car) {
        return bikeRepo.save(car);
    }


    @Transactional
    public void dellById(Long id){
        bikeRepo.deleteById(id);
    }
}
