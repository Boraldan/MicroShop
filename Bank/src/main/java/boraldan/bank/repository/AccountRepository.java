package boraldan.bank.repository;


import boraldan.entitymicro.bank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, Long> {
   BankAccount findByCard(Long card);
}
