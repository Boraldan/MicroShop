package boraldan.cart.controller.feign;

import boraldan.entitymicro.shop.entity.item.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;
@Primary
@FeignClient(name = "shop", fallback = ShopFeignFallback.class)
public interface ShopFeign {
    @PostMapping("/uuidlist")
    ResponseEntity<List<Item>> getByUuidList(@RequestBody List<UUID> uuidList);
}

@Component
class ShopFeignFallback implements ShopFeign {
    @Override
    public ResponseEntity<List<Item>> getByUuidList(List<UUID> uuidList) {
        return null;
    }
}