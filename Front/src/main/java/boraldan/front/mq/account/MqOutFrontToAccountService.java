package boraldan.front.mq.account;

import boraldan.entitymicro.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class MqOutFrontToAccountService {

    private final MqOutFrontToAccountConfig mqOutFrontToAccountConfig;

    /**
     * sendMessage рабочий метод для отправки сообщений
     *
     * @param cartDto объект, который хотим передать
     */
    public void sendMessage(CartDto cartDto, String jwtToken) {

        mqOutFrontToAccountConfig.getTunnelBus().emitNext(MessageBuilder.withPayload(cartDto)
                        .setHeader("Authorization", "Bearer " + jwtToken)
                        .build(),
                Sinks.EmitFailureHandler.FAIL_FAST);

        System.out.printf("Сообщение отправлено: %s  -- > %s%n", cartDto.getCustomer().getEmail(), jwtToken);
    }


////    // еще такой вариант   отправки  --------------------
//    private final RabbitTemplate rabbitTemplate;
//
//    public void sendMessageRabbitTemplate(CartDto cartDto, String jwtToken) {
//        Message<CartDto> message = MessageBuilder.withPayload(cartDto)
//                .setHeader("Authorization", "Bearer " + jwtToken)
//                .build();
//        rabbitTemplate.convertAndSend("account-destination", message);
//    }
}
