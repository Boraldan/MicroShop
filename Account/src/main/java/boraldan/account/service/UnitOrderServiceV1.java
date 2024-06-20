package boraldan.account.service;

import boraldan.account.repository.order.OrderUnitRepo;
import boraldan.account.service.i_service.UnitOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UnitOrderServiceV1 implements UnitOrderService {

    private final OrderUnitRepo orderUnitRepo;


}
