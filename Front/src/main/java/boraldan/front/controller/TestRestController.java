package boraldan.front.controller;

import boraldan.entitymicro.cart.entity.Cart;
import boraldan.front.redis.RedisService;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class TestRestController {

    private final HttpSession session;
    private final RedisService redisService;
//    private final HeaderService headerService;



    @GetMapping
    public Integer test(Model model, @AuthenticationPrincipal Principal principal) {
        redisService.getOpsForValue().set(session.getId(), new Cart());


//        StringBuilder attributes = new StringBuilder();
//        Enumeration<String> attributeNames = session.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String attributeName = attributeNames.nextElement();
//            Object attributeValue = session.getAttribute(attributeName);
//            attributes.append(attributeName).append(": ").append(attributeValue).append("\n");
//        }
//        System.out.println(attributes);


//        return testItem.getItem();
        return 1;
//        return authentication.getAuthorities().stream().toList().get(0).toString();
    }

    @GetMapping("/keys")
    public Set<String> keys() {
        return redisService.getRedisTemplate().keys("*");
    }


    @GetMapping("/redisclean")
    public String redisClean() {

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

}
