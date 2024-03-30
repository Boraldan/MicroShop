package boraldan.storage.controller;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.repository.StorageJpaRepository;
import boraldan.storage.service.i_service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService<CarStorage, StorageJpaRepository<CarStorage>> carStorageService;
    private final StorageService<Storage, StorageJpaRepository<Storage>> storageService;


    @PostMapping("car/quantity")
    public ResponseEntity<CarStorage> carQuantity(@RequestBody Car car){
        System.out.println("Stotage --> " + car);
        return new ResponseEntity<>(carStorageService.getById(car.getStorageId()), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Storage>> all(){
        return new ResponseEntity<>(storageService.getAll(), HttpStatus.OK);
    }

    @GetMapping("car2")
    public ResponseEntity<?> car2(@RequestParam ("id")UUID id){
        return new ResponseEntity<>(carStorageService.getById(id), HttpStatus.OK);
    }

    @PostMapping("addcarstor")
    public ResponseEntity<Storage> addCar(@RequestBody StorageDto storageDto){
        System.out.println("Stotage --> " + storageDto);
//        CarStorage carStorage = carStorageService.saveNew(storageDto);
        return new ResponseEntity<>(carStorageService.saveNew(storageDto), HttpStatus.OK);
    }

}
