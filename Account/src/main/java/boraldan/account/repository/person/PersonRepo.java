package boraldan.account.repository.person;

import boraldan.entitymicro.account.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepo extends JpaRepository<Person, UUID> {
}
