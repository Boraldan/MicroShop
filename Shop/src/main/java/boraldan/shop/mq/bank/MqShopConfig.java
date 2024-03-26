package boraldan.shop.mq.bank;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;
import java.util.function.Supplier;


@Configuration
@Getter
public class MqShopConfig {

    private final Sinks.Many<Message<Long>> tunnelBus = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    @Bean
    public Supplier<Flux<Message<Long>>> newMessageShop(){
        return tunnelBus::asFlux;
    }
}
