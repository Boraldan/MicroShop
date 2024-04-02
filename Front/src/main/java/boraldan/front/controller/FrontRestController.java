package boraldan.front.controller;

import boraldan.entitymicro.cart.dto.CartsDTO;
import boraldan.entitymicro.cart.entity.Cart;
import boraldan.entitymicro.shop.dto.ListItemDto;
import boraldan.entitymicro.test.Fly;
import boraldan.front.service.HeaderService;
import boraldan.front.service.IServiceApi;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
public class FrontRestController {

    private final IServiceApi serviceApi;
    private final HttpSession session;
    private final RedisTemplate<String, Object> redisTemplate;
    private final HeaderService headerService;


    @GetMapping("/test")
    public Integer test(Model model, @AuthenticationPrincipal Principal principal) {

        if (session.getAttribute("Open session") == null & principal == null) {
            session.setAttribute("Open session", "Value open session");
            Fly fly = new Fly();
            fly.setNumber(TestCount.getCount());
            redisTemplate.opsForValue().set(session.getId(), fly);

        }

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication);


//        TestItem testItem = (TestItem) session.getAttribute("item");
//        System.out.println("сессия front -->  " + session.getId());
//
        String sessionShop = serviceApi.getSession(session.getId());
        System.out.println("сессия shop 1 -->  " + sessionShop);

//
//
//        StringBuilder attributes = new StringBuilder();
//        Enumeration<String> attributeNames = session.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String attributeName = attributeNames.nextElement();
//            Object attributeValue = session.getAttribute(attributeName);
//            attributes.append(attributeName).append(": ").append(attributeValue).append("\n");
//        }
//        System.out.println(attributes);
//
//
////        System.out.println("redis 1 --> " + redisTemplate.opsForValue().get(sessionShop));
//        System.out.println("redis 2 --> " + redisTemplate.opsForList().rightPop("s2"));


//        return testItem.getItem();
        return 1;
//        return authentication.getAuthorities().stream().toList().get(0).toString();
    }

    @GetMapping("/keys")
    public Set<String> keys(){
        return redisTemplate.keys("*");
    }



    @GetMapping("/redis")
    public String redis() {

        RedisClient client = RedisClient.create("redis://localhost");
        // Подключение к Redis
        try (StatefulRedisConnection<String, String> connection = client.connect()) {
            RedisCommands<String, String> commands = connection.sync();
            // Очистка всех данных из текущей базы данных
            commands.flushdb();
            System.out.println("Данные из Redis успешно очищены.");
        } finally {
            // Закрытие клиента Redis
            client.shutdown();
        }
        return "ok";
    }


//    @GetMapping("/car")
//    public String car() {
//        return serviceApi.getCar().toString();
//    }

    @GetMapping("/fly")
    public String fly() {
        return serviceApi.getFly().toString();
    }

    @GetMapping("/items")
    public ResponseEntity<ListItemDto> items() {
        return new ResponseEntity<>(serviceApi.getItems(), HttpStatus.OK);
    }

    @GetMapping("/carts")
    public List<Cart> carts() {
        CartsDTO cartsDTO = serviceApi.getCarts();
        System.out.println(cartsDTO);
        return cartsDTO.getCarts();
    }

    @GetMapping("/card")
    public String getByCard() {
        return serviceApi.getCard();
    }
}
