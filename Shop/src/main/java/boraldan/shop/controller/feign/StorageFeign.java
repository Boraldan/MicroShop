package boraldan.shop.controller.feign;


import boraldan.entitymicro.storage.dto.ListStorageDto;
import boraldan.entitymicro.storage.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(name = "storage", fallback = StorageFeignFallback.class)
public interface StorageFeign {

    @PostMapping("/quantity")
    ResponseEntity<Storage> getQuantity(@RequestBody Storage storage);

    @PostMapping("/list")
    ResponseEntity<ListStorageDto> getByList(@RequestBody ListStorageDto listStorageDto);

    @PostMapping("/listbycategory")
    ResponseEntity<ListStorageDto> getListByCategory(@RequestBody ListStorageDto listStorageDto);

    @PutMapping("/save")
    ResponseEntity<Storage> saveItem(@RequestBody Storage storage);

    @DeleteMapping("/dell")
    ResponseEntity<Void> dellItem(@RequestBody Storage storage);

}

/**
 * Spring Cloud Circuit Breaker  для обработки ошибок, если сервис недоступен.
 */
@Component
class StorageFeignFallback implements StorageFeign {

    // можно вернуть, что нам надо для дальнейшей обработки
    @Override
    public ResponseEntity<Storage> getQuantity(Storage storage) {
        return new ResponseEntity<>(new Storage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ListStorageDto> getByList(@RequestBody ListStorageDto listStorageDto) {
        return new ResponseEntity<>(new ListStorageDto(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ListStorageDto> getListByCategory(ListStorageDto listStorageDto) {
        return new ResponseEntity<>(new ListStorageDto(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Storage> saveItem(@RequestBody Storage storage) {
        return new ResponseEntity<>(new Storage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> dellItem(Storage storage) {
        return ResponseEntity.badRequest().build();
    }
}
