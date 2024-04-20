package boraldan.account.controller.feign;


import boraldan.entitymicro.cart.entity.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Primary
@FeignClient(name = "cart", fallback = CartFeignFallback.class)
public interface CartFeign {

    @GetMapping("/getnew")
    ResponseEntity<Cart> getNewCart();
}

@Component
class CartFeignFallback implements CartFeign{

    @Override
    public ResponseEntity<Cart> getNewCart() {
        return null;
    }
}