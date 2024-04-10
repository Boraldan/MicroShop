package boraldan.account.controller.feign;

import boraldan.entitymicro.shop.dto.ListItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shop", fallback = ShopFeignFallback.class)
public interface ShopFeign {

    @PostMapping("/itemlist")
    ResponseEntity<ListItemDto> getByItemList(@RequestBody ListItemDto listItemDto);
}


@Component
class ShopFeignFallback implements ShopFeign {

    // в случае ошибки, можно вернуть, что нам надо для дальнейшей обработки
    @Override
    public ResponseEntity<ListItemDto> getByItemList(@RequestBody ListItemDto listItemDto) {
        return new ResponseEntity<>(new ListItemDto(), HttpStatus.BAD_REQUEST);
    }
}