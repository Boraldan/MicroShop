package boraldan.bank.mq;

import boraldan.bank.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;


@AllArgsConstructor
@Configuration
public class MessageBankConfig {

    private final TransferService transferService;

    @Bean
    public Consumer<Message<Long>> inputMessageBank(){
       return message -> System.out.println(transferService.getByCard(message.getPayload()));
    }
}
