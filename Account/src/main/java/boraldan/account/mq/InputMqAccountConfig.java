package boraldan.account.mq;

import boraldan.entitymicro.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;


@RequiredArgsConstructor
@Configuration
public class InputMqAccountConfig {

    private final InputMqAccountService inputMqAccountService;


    @Bean
    public Consumer<Message<CartDto>> inputFrontToAccount() {
        return message -> {
            CartDto cartDto = message.getPayload();
            inputMqAccountService.inputCartDtoFrontToAccount(cartDto);
        };
    }


//    private final SecurityContextManager securityContextManager;

//    @Bean
//    public Consumer<Message<String>> inputMessageFrontToAccount() {
    //        return message -> {
//            MessageHeaders headers = message.getHeaders();
//            String token = (String) headers.get("Authorization");
//            if (token != null && token.startsWith("Bearer ")) {
//                token = token.substring(7);
//                securityContextManager.runWithSecurityContext(token, () -> {
//                    System.out.println(message.getPayload());
//                });
//
//            } else {
//                System.out.println("Authorization header is missing or invalid.");
//            }
//        };
//        return message -> System.out.println(message.getPayload());
//    }
}
