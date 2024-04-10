package boraldan.account.service;

import boraldan.account.repository.person.PersonRepo;
import boraldan.account.service.i_service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonServiceV1 implements PersonService {
    private final PersonRepo personRepo;
}
