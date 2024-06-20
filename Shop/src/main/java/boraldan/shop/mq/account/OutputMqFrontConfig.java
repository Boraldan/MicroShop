package boraldan.shop.mq.account;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.util.function.Supplier;


@Getter
@Configuration
public class OutputMqFrontConfig {

    private final Sinks.Many<Message<String>> tunnelBus = Sinks.many().multicast().onBackpressureBuffer(Queues.SMALL_BUFFER_SIZE, false);

    /**
     * outputMessageFrontToAccount название канала для настроек application.yaml
     * @return
     */

    @Bean
    public Supplier<Flux<Message<String>>> outputMessageFrontToAccount(){
        return tunnelBus::asFlux;
    }
}
