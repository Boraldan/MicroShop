package boraldan.storage.controller;

import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import boraldan.storage.service.CarStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
public class StorageController {

    private final CarStorageService carStorageService;

    @PostMapping("car/quantity")
    public ResponseEntity<CarStorage> carQuantity(@RequestBody Car car){
        log.info("Stotage --> " + car);
        return new ResponseEntity<>(carStorageService.getByCarId(car.getId()), HttpStatus.OK);
    }

}
