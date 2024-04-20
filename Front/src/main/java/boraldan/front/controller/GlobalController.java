package boraldan.front.controller;

import boraldan.entitymicro.account.entity.person.Customer;
import boraldan.entitymicro.cart.entity.Cart;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.AccountRestClient;
import boraldan.front.rest_client.CartRestClient;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalController {

    private final RedisService redisService;
    private final CartRestClient cartRestClient;
    private final AccountRestClient accountRestClient;
    private final HttpSession httpSession;

    @ModelAttribute("cart")
    public Cart setCart(Principal principal) {

        System.out.println(" @ModelAttribute(cart)" + principal);

        Cart cart;
        if (principal != null) {

            System.out.println(" -2  GlobalController  principal --> " + principal.getName());

            cart = redisService.getCart(principal.getName());
            if (cart == null) {
                Customer customer = accountRestClient.getCustomerByUsername(principal.getName());

                System.out.println(" -1 GlobalController  customer --> " + customer);

                cart = cartRestClient.getCart(customer.getCartId());

                System.out.println(" 0 cart principal -->  " + cart);
// TODO: 20.04.2024 проверись есть ли товары в корзине в сессии которая была до авторизации

                concatCart(cart);

                redisService.setCart(principal.getName(), cart);

                System.out.println(" 1 cart principal -->  " + cart);

                return cart;
            }

            return cart;

        } else {
            cart = redisService.getCart(httpSession.getId());
            if (cart == null) {
                cart = new Cart();
                cart.setName("anonymous");
                redisService.setCart(httpSession.getId(), cart);

                Cart cart123 = redisService.getCart(httpSession.getId());
                System.out.println(" 2 cart anonimys -->  " + httpSession.getId() + "    " + cart123);

                return cart;
            } else {
                System.out.println(" 3 cart anonimys -->  " + httpSession.getId() + "    " + cart);
                return cart;
            }
        }
    }

    private void concatCart(Cart newCart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("MainRedisHttpInterceptor   authentication  -->  " + authentication);
        String sessionId = "";
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            sessionId = ((WebAuthenticationDetails) authentication.getDetails()).getSessionId();
            System.out.println("Session ID: " + sessionId);
        }

        Cart oldCart = redisService.getCart(sessionId);
        if (oldCart != null) {
            if (!oldCart.getUnitCart().isEmpty()) {
                newCart.getUnitCart().addAll(oldCart.getUnitCart());
            }
            redisService.deleteCart(sessionId);
        }


    }


//        Cart cart;
//        if (principal != null) {
//
//            System.out.println( " -2  GlobalController  principal --> " +  principal.getName());
//
//            cart = redisService.getCart(principal.getName());
//            if (cart == null) {
//                Customer customer = accountRestClient.getCustomerByUsername(principal.getName());
//
//                System.out.println( " -1 GlobalController  customer --> " +  customer);
//
//                cart = cartRestClient.getCart(customer.getCartId());
//
//                System.out.println(" 0 cart principal -->  " + cart);
//
//                redisService.setCart(principal.getName(), cart);
//
//                System.out.println(" 1 cart principal -->  " + cart);
//
//                return cart;
//            }
//            return cart;
//
//        } else {
//            cart = redisService.getCart(httpSession.getId());
//            if (cart == null) {
//                cart = new Cart();
//                cart.setName("anonymous");
//                redisService.setCart(httpSession.getId(), cart);
//
//                Cart cart123 = redisService.getCart(httpSession.getId());
//                System.out.println(" 2 cart anonimys -->  " + httpSession.getId() + "    " + cart123);
//
//                return cart;
//            } else {
//                System.out.println(" 3 cart anonimys -->  " + httpSession.getId() + "    " + cart);
//                return cart;
//            }
//        }
}
