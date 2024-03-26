package boraldan.bank.repository;


import boraldan.entitymicro.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
   Account findByCard(Long card);
}
