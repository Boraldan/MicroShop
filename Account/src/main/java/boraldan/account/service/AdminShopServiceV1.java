package boraldan.account.service;

import boraldan.account.repository.person.AdminShopRepo;
import boraldan.account.repository.person.CustomerRepo;
import boraldan.account.repository.person.PersonRepo;
import boraldan.account.service.i_service.AdminShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminShopServiceV1 implements AdminShopService {
    private final AdminShopRepo adminShopRepo;
    private final CustomerRepo customerRepo;
    private final PersonRepo personRepo;
}
