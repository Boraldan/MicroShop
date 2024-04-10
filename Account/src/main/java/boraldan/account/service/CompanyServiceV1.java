package boraldan.account.service;

import boraldan.account.repository.seller.CompanyRepo;
import boraldan.account.service.i_service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyServiceV1 implements CompanyService {

    private final CompanyRepo companyRepo;
}
