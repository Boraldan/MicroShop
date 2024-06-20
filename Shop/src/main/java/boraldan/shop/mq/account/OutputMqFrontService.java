package boraldan.shop.mq.account;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class OutputMqFrontService {

    private final OutputMqFrontConfig outputMqFrontConfig;


    public void sendMessage( String tokenValue) {

        outputMqFrontConfig.getTunnelBus().emitNext(MessageBuilder.withPayload("message from shop")
                        .setHeader("Authorization",  tokenValue)
                        .build(),
                Sinks.EmitFailureHandler.FAIL_FAST);

        System.out.printf("Сообщение отправлено:  -- > %s%n",   tokenValue);
    }


////    // еще такой вариант   отправки  ------------------------------------
//    private final RabbitTemplate rabbitTemplate;
//
//    public void sendMessageRabbitTemplate(CartDto cartDto, String jwtToken) {
//        Message<CartDto> message = MessageBuilder.withPayload(cartDto)
//                .setHeader("Authorization", "Bearer " + jwtToken)
//                .build();
//        rabbitTemplate.convertAndSend("account-destination", message);
//    }
}
