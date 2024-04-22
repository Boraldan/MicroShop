package boraldan.front.session;

import boraldan.entitymicro.cart.entity.Cart;
import boraldan.front.redis.RedisService;
import boraldan.front.rest_client.CartRestClient;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MySessionListener implements HttpSessionListener {
    private final String REDIS_KEY = "REDIS_KEY";
    private final RedisService redisService;
    private final CartRestClient cartRestClient;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(100);
        event.getSession().setAttribute(REDIS_KEY, event.getSession().getId());
        Cart cart = new Cart();
        cart.setOwnerName("anonymous");
        redisService.setCart(event.getSession().getId(), cart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String usernameOrSessionId = (String) event.getSession().getAttribute(REDIS_KEY);
        if (event.getSession().getId().equals(usernameOrSessionId)) {
            redisService.deleteCart(event.getSession().getId());
        } else {
            Cart cart = redisService.getOpsForValue().getAndDelete(usernameOrSessionId);
            cartRestClient.saveCartSession(cart);
        }
    }
}