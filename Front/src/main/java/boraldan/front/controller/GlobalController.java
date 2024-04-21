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
        } else {

            cart = redisService.getCart(httpSession.getId());
//            if (cart == null) {
//                cart = new Cart();
//                cart.setOwnerName("anonymous");
//                redisService.setCart(httpSession.getId(), cart);
//                return cart;
//            } else {
            return cart;
        }
    }
//    }

    private void concatCart(Cart newCart) {

        String oldSessionId = (String) httpSession.getAttribute(REDIS_KEY);

        System.out.println("oldSessionId  -->  " +  oldSessionId);

        Cart oldCart = redisService.getCart(oldSessionId);
        System.out.println("old Card  -->  " +  oldCart);

        if (oldCart != null) {
            // TODO: 21.04.2024 проверить по позициям логику сложения Cart
            if (!oldCart.getUnitCart().isEmpty()) {
                newCart.getUnitCart().addAll(oldCart.getUnitCart());
            }
            redisService.deleteCart(oldSessionId);
        }


    }
//
//    @ModelAttribute("cart")
//    public Cart setCart(Principal principal) {
//        Cart cart;
//        if (principal != null) {
//
//            cart = redisService.getCart(principal.getName().toLowerCase());
//            if (cart == null) {
//
//                Customer customer = accountRestClient.getCustomerByUsername(principal.getName());
//                cart = cartRestClient.getCart(customer.getCartId());
//
//
//
//
//                concatCart(cart);
//
//                redisService.setCart(principal.getName().toLowerCase(), cart);
//
//                httpSession.setAttribute("username", principal.getName().toLowerCase());
//
//                return cart;
//            }
//
//
//            return cart;
//        } else {
//            cart = redisService.getCart(httpSession.getId());
//            if (cart == null) {
//                cart = new Cart();
//                cart.setOwnerName("anonymous");
//                redisService.setCart(httpSession.getId(), cart);
//                return cart;
//            } else {
//                return cart;
//            }
//        }
//    }
//
//    private void concatCart(Cart newCart) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String sessionId = "";
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            sessionId = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
//        }
//// TODO: 21.04.2024 проверить подгружается ли сессия из authentication
//        System.out.println("старая сессия  -- >  " + sessionId );
//
//        Cart oldCart = redisService.getCart(sessionId);
//        if (oldCart != null) {
//            if (!oldCart.getUnitCart().isEmpty()) {
//                newCart.getUnitCart().addAll(oldCart.getUnitCart());
//            }
//            redisService.deleteCart(sessionId);
//        }
//
//
//    }
}
