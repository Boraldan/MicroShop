package boraldan.account.mq;

import boraldan.account.service.OrderServiceV1;
import boraldan.entitymicro.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InputMqAccountService {

    private final OrderServiceV1 orderService;

    public void inputCartDtoFrontToAccount(CartDto cartDto) {
        System.out.println("InputMqAccountService --> " + cartDto);

        orderService.creatOrder(cartDto);
    }



}
