package boraldan.account.controller.feign;

import boraldan.entitymicro.storage.dto.ReserveDtoList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Primary
@FeignClient(name = "storage", fallback =StorageFeignFallback.class)
public interface StorageFeign {

   @PutMapping("/reserve/sale")
    ResponseEntity<Void> deleteReserveAfterSale(@RequestBody ReserveDtoList reserveDtoList);

}

class StorageFeignFallback implements StorageFeign{

    @Override
    public ResponseEntity<Void> deleteReserveAfterSale(ReserveDtoList reserveDtoList) {
        return null;
    }
}