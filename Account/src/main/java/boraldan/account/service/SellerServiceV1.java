package boraldan.account.service;

import boraldan.account.repository.seller.SellerRepo;
import boraldan.account.service.i_service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SellerServiceV1 implements SellerService {

    private final SellerRepo sellerRepo;
}
