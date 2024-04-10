package boraldan.account.service;

import boraldan.account.repository.seller.SoleTraderRepo;
import boraldan.account.service.i_service.SoleTraderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SoleTraderServiceV1 implements SoleTraderService {

    private final SoleTraderRepo soleTraderRepo;
}
