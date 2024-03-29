package boraldan.storage.controller;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.StorageJpaRepository;
import boraldan.storage.repository.StorageRepo;
import boraldan.storage.service.i_service.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
public class StorageController {

    private final StorageService<CarStorage, StorageJpaRepository<CarStorage>> carStorageService;
    private final StorageService<Storage, StorageJpaRepository<Storage>> storageService;

    private final StorageRepo storageRepo;

    @PostMapping("car/quantity")
    public ResponseEntity<CarStorage> carQuantity(@RequestBody Car car){
        System.out.println("Stotage --> " + car);
        return new ResponseEntity<>(carStorageService.getByItemId(car.getId()), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Storage>> all(){
        return new ResponseEntity<>(storageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("car2")
    public ResponseEntity<?> car2(){
        return new ResponseEntity<>(carStorageService.getByItemId(2L), HttpStatus.OK);
    }

}
