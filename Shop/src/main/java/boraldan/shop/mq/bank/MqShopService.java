package boraldan.shop.mq.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@Getter
@AllArgsConstructor
public class MqShopService {

    private final MqShopConfig mqShopConfig;

    public void sendMessage(Long card) {
        mqShopConfig.getTunnelBus().emitNext(MessageBuilder.withPayload(card).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        System.out.println("Сообщение отправлено: " + card);
    }
}
