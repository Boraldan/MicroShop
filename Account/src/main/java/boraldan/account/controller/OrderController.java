package boraldan.account.controller;

import boraldan.account.service.OrderServiceV1;
import boraldan.entitymicro.account.entity.order.Order;
import boraldan.entitymicro.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderServiceV1 orderService;

    @PostMapping("/creat")
    public ResponseEntity<Order> creatOrder(@RequestBody CartDto cartDto) {
        System.out.println("/creat --> " + cartDto);
        Order order = orderService.creatOrder(cartDto);
        return ResponseEntity.ok(order);
    }

}
