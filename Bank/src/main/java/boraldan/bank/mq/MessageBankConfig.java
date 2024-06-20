package boraldan.bank.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;


@RequiredArgsConstructor
@Configuration
public class MessageBankConfig {

//    private final TransferService transferService;

    @Bean
    public Consumer<Message<Long>> inputFrontToBank(){
//       return message -> System.out.println(transferService.getByCard(message.getPayload()));
       return message -> System.out.println(message.getPayload());
    }
}
