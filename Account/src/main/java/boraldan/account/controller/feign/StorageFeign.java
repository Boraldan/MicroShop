package boraldan.account.controller.feign;


import boraldan.entitymicro.shop.entity.item.transport.car.Car;
import boraldan.entitymicro.storage.entity.transport.car.CarStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "storage")
public interface StorageFeign {

    @PostMapping("/quantity")
    ResponseEntity<CarStorage> getQuantity(@RequestBody Car car);

//
//    @PostMapping("/transfershop")
//    ResponseEntity<?> transferShop(@RequestBody PayDTO payDTO);
}
