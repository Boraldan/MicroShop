package boraldan.shop.controller.feign;


import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.dto.StorageAllDto;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(name = "storage", fallback = StorageFeignFallback.class)
public interface StorageFeign {

    @PostMapping("car/quantity")
    ResponseEntity<CarStorage> getQuantity(@RequestBody Car car);

    //    @PostMapping("/transfershop")
//    ResponseEntity<?> transferShop(@RequestBody PayDTO payDTO);
    @GetMapping("all")
    ResponseEntity<?> all();
}

/**
 * Spring Cloud Circuit Breaker  для обработки ошибок, если сервис недоступен.
 */
@Component
class StorageFeignFallback implements StorageFeign {

    // можно вернуть, что нам надо для дальнейшей обработки
    @Override
    public ResponseEntity<CarStorage> getQuantity(Car car) {
        return new ResponseEntity<>(new CarStorage(), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(new StorageAllDto(), HttpStatus.OK);
    }
}
