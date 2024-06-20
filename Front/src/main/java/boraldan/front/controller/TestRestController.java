package boraldan.front.controller;

import boraldan.entitymicro.cart.dto.CartDto;
import boraldan.front.mq.account.MqOutFrontToAccountService;
import boraldan.front.mq.bank.MqOutFrontToBankService;
import boraldan.front.redis.RedisService;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private final MqOutFrontToAccountService mqOutFrontToAccountService;
    private final MqOutFrontToBankService mqOutFrontToBankService;


    @GetMapping("/mq")
    public ResponseEntity<String> test(Principal principal,
                                       @ModelAttribute("cartDto") CartDto cartDto,
                                       @RegisteredOAuth2AuthorizedClient("keycloak") OAuth2AuthorizedClient authorizedClient
    ) {

        if (principal != null) {
            String jwtToken = authorizedClient.getAccessToken().getTokenValue();
            mqOutFrontToAccountService.sendMessage(cartDto, jwtToken);
            return ResponseEntity.ok(cartDto.getCustomer().getEmail());
        }
        return ResponseEntity.ok("principal = null");
    }

    @GetMapping("/mq-bank")
    public ResponseEntity<String> testBank() {

        mqOutFrontToBankService.sendMessage(202020202L);

        return ResponseEntity.ok("bank sent 202020202L");
    }


    @GetMapping
    public Integer test(Model model, @AuthenticationPrincipal Principal principal) {
        redisService.getOpsForValue().set(session.getId(), new CartDto());


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
