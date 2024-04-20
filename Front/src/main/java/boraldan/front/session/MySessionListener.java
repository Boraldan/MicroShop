package boraldan.front.session;

import boraldan.entitymicro.cart.entity.Cart;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.CartRestClient;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MySessionListener implements HttpSessionListener {

    private final RedisService redisService;
    private final CartRestClient cartRestClient;


    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(20);
        System.out.println("Session Created: " + event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauth2User = oauthToken.getPrincipal();
            username = oauth2User.getName();
        }
        if (username.isBlank()) {
            System.out.println("MySessionListener username --> " + username);
            redisService.deleteCart(event.getSession().getId());
        } else {
            Cart cart = redisService.getOpsForValue().getAndDelete(username);
            cartRestClient.saveCartSession(cart);
            System.out.println("Удалили из Redis  и  записали новую в Cart-app");
        }

        System.out.println("Session Destroyed: " + event.getSession().getId());
    }
}