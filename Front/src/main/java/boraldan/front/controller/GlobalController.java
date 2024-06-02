package boraldan.front.controller;

import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.entitymicro.cart.dto.CartUnitDto;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.AccountRestClient;
import boraldan.front.rest_client.CartRestClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalController {

    private final String REDIS_KEY = "REDIS_KEY";
    private final RedisService redisService;
    private final CartRestClient cartRestClient;
    private final AccountRestClient accountRestClient;
    private final HttpSession httpSession;

    @ModelAttribute("cartDto")
    public CartDto setCart(Principal principal) {
        CartDto cartDto;
        if (principal != null) {
            cartDto = redisService.getCart(principal.getName().toLowerCase()); // корзина из redis
            if (cartDto == null) {
                Customer customer = accountRestClient.getCustomerAccount();
                cartDto = cartRestClient.getCart(customer); // корзина из db
                concatCart(cartDto, principal.getName().toLowerCase());
            } else {
                concatCart(cartDto, principal.getName().toLowerCase());
            }
            redisService.setCart(principal.getName().toLowerCase(), cartDto);
            httpSession.setAttribute(REDIS_KEY, principal.getName().toLowerCase());
            return cartDto;
        }
        cartDto = redisService.getCart(httpSession.getId());
        return cartDto;
    }

    private void concatCart(CartDto cartDto, String customerName) {
        String oldSessionId = (String) httpSession.getAttribute(REDIS_KEY);
        if (oldSessionId.equals(customerName)) return;
        CartDto oldCartRedis = redisService.getCart(oldSessionId);
        if (oldCartRedis != null) {
            if (!oldCartRedis.getCartUnitDtoList().isEmpty()) {
                for (CartUnitDto cartUnitDto : oldCartRedis.getCartUnitDtoList()) {
                    cartDto.getCartUnitDtoList().stream()
                            .filter(unit -> unit.getItem().getId().equals(cartUnitDto.getItem().getId()))
                            .findFirst()
                            .ifPresentOrElse(unit -> unit.setUnitQuantity(unit.getUnitQuantity() + cartUnitDto.getUnitQuantity()),
                                    () -> cartDto.getCartUnitDtoList().add(cartUnitDto));
                }
            }
            redisService.deleteCart(oldSessionId);
        }
    }


//    @ExceptionHandler(HttpClientErrorException.NotFound.class)
//    public ResponseEntity<String> handleNotFound(HttpClientErrorException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ресурс не найден");
//    }


}
