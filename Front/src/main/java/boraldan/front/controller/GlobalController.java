package boraldan.front.controller;

import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.dto.CartDto;
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

    @ModelAttribute("cart")
    public CartDto setCart(Principal principal) {
        CartDto cartDto;
        if (principal != null) {
            cartDto = redisService.getCart(principal.getName().toLowerCase());
            if (cartDto == null) {
                Customer customer = accountRestClient.getCustomerAccount();
                cartDto = cartRestClient.getCart(customer);
                // TODO: 21.04.2024 проверить по позициям логику сложения Cart
                concatCart(cartDto);
                redisService.setCart(principal.getName().toLowerCase(), cartDto);
                httpSession.setAttribute(REDIS_KEY, principal.getName().toLowerCase());
                return cartDto;
            }
            return cartDto;
        }
        cartDto = redisService.getCart(httpSession.getId());
        return cartDto;
    }

    private void concatCart(CartDto newCart) {
        String oldSessionId = (String) httpSession.getAttribute(REDIS_KEY);
        CartDto oldCart = redisService.getCart(oldSessionId);
        if (oldCart != null) {
            // TODO: 21.04.2024 проверить по позициям логику сложения Cart
            if (!oldCart.getCartUnitDtoList().isEmpty()) {
                newCart.getCartUnitDtoList().addAll(oldCart.getCartUnitDtoList());
            }
            redisService.deleteCart(oldSessionId);
        }
    }


//    @ExceptionHandler(HttpClientErrorException.NotFound.class)
//    public ResponseEntity<String> handleNotFound(HttpClientErrorException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ресурс не найден");
//    }






}
