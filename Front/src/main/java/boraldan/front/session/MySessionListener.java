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
        event.getSession().setMaxInactiveInterval(20);

        event.getSession().setAttribute(REDIS_KEY, event.getSession().getId());

        Cart cart = new Cart();
        cart.setOwnerName("anonymous");

        redisService.setCart(event.getSession().getId(), cart);


        System.out.println("Session Created: " + event.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

//    не применима в этом месте, не подтягивается  SecurityContextHolder.getContext().getAuthentication();
//        String username = "";
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof OAuth2AuthenticationToken) {
//            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//            OAuth2User oauth2User = oauthToken.getPrincipal();
//            username = oauth2User.getName();
//        }



        System.out.println("MySessionListener 1  --> " +   event.getSession().getId());
        String OLD_REDIS_KEY = (String) event.getSession().getAttribute(REDIS_KEY);
        System.out.println("MySessionListener 2  --> " + OLD_REDIS_KEY);

        if (event.getSession().getId().equals(OLD_REDIS_KEY)){
            redisService.deleteCart(event.getSession().getId());
            System.out.println("MySessionListener 3  -->  удалили Redis anonymous " + OLD_REDIS_KEY);

        } else {
            Cart cart = redisService.getOpsForValue().getAndDelete(OLD_REDIS_KEY);
            System.out.println("MySessionListener 4  -->"  +  cart );
            cartRestClient.saveCartSession(cart);
            System.out.println("MySessionListener 5  -->  удалили Redis пользователя " + OLD_REDIS_KEY);
        }

        System.out.println("Session Destroyed: " + event.getSession().getId());
    }
}