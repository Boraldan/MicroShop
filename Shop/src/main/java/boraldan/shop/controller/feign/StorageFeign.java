package boraldan.shop.controller.feign;


import boraldan.entitymicro.storage.entity.Storage;
import boraldan.entitymicro.storage.entity.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.dto.StorageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(name = "storage", fallback = StorageFeignFallback.class)
public interface StorageFeign {

    @PostMapping("/quantity")
    ResponseEntity<Storage> getQuantity(@RequestBody StorageDto storageDto);


    @PostMapping("/list")
    ResponseEntity<ListStorageDto> getByList(@RequestBody ListStorageDto listStorageDto);

    @PutMapping("/save")
    ResponseEntity<Storage> saveItem(@RequestBody StorageDto storageDto);


}

/**
 * Spring Cloud Circuit Breaker  для обработки ошибок, если сервис недоступен.
 */
@Component
class StorageFeignFallback implements StorageFeign {

    // можно вернуть, что нам надо для дальнейшей обработки
    @Override
    public ResponseEntity<Storage> getQuantity(StorageDto storageDto) {
        return new ResponseEntity<>(new Storage(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListStorageDto> getByList(@RequestBody ListStorageDto listStorageDto) {
        return new ResponseEntity<>(new ListStorageDto(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Storage> saveItem(@RequestBody StorageDto storageDto) {
        return new ResponseEntity<>(new Storage(), HttpStatus.OK);
    }
}
