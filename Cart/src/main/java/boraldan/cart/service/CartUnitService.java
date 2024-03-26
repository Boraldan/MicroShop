package boraldan.cart.service;

import boraldan.cart.repository.CartRepo;
import boraldan.cart.repository.CartUnitRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CartUnitService {

    private final CartUnitRepo cartUnitRepo;
}
