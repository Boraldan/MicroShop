package boraldan.front.controller;

import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.entity.Cart;
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
    public Cart setCart(Principal principal) {
        Cart cart;
        if (principal != null) {
            cart = redisService.getCart(principal.getName().toLowerCase());
            if (cart == null) {
                Customer customer = accountRestClient.getCustomerByUsername(principal.getName());
                cart = cartRestClient.getCart(customer.getCartId());
                cart.setOwnerName(principal.getName());
                concatCart(cart);
                redisService.setCart(principal.getName().toLowerCase(), cart);
                httpSession.setAttribute(REDIS_KEY, principal.getName().toLowerCase());
                return cart;
            }
            return cart;
        }
        cart = redisService.getCart(httpSession.getId());
        return cart;
    }

    private void concatCart(Cart newCart) {
        String oldSessionId = (String) httpSession.getAttribute(REDIS_KEY);
        Cart oldCart = redisService.getCart(oldSessionId);
        if (oldCart != null) {
            // TODO: 21.04.2024 проверить по позициям логику сложения Cart
            if (!oldCart.getUnitCart().isEmpty()) {
                newCart.getUnitCart().addAll(oldCart.getUnitCart());
            }
            redisService.deleteCart(oldSessionId);
        }
    }

}
