package boraldan.front.session;

import boraldan.entitymicro.cart.dto.CartDto;
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
        int maxInactiveInterval = 70;
        event.getSession().setMaxInactiveInterval(maxInactiveInterval);
        event.getSession().setAttribute(REDIS_KEY, event.getSession().getId());
        CartDto cartDto = new CartDto();
        redisService.setCart(event.getSession().getId(), cartDto);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String usernameOrSessionId = (String) event.getSession().getAttribute(REDIS_KEY);
        System.out.println("sessionDestroyed - usernameOrSessionId -- > " + usernameOrSessionId);
        if (event.getSession().getId().equals(usernameOrSessionId)) {
            redisService.deleteCart(event.getSession().getId());
        } else {
            CartDto cartDto =  redisService.getOpsForValue().getAndDelete(usernameOrSessionId);
            cartRestClient.saveCart(cartDto);
        }
    }
}