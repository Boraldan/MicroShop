package boraldan.shop.controller.feign;


import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.fabric.CarStorageFabric;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Primary
@FeignClient(name = "storage", fallback = StorageFeignFallback.class)
public interface StorageFeign {

    @PostMapping("car/quantity")
    ResponseEntity<CarStorage> getQuantity(@RequestBody Car car);

//    @PostMapping("/transfershop")
//    ResponseEntity<?> transferShop(@RequestBody PayDTO payDTO);

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

}
