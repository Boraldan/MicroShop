package boraldan.account.service;


import boraldan.account.repository.order.OrderRepo;
import boraldan.account.service.i_service.OrderService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceV1 implements OrderService {

    private final OrderRepo orderRepo;


}