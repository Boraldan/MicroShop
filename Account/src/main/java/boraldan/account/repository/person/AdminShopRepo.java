package boraldan.account.repository.person;

import boraldan.entitymicro.account.entity.person.AdminShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminShopRepo extends JpaRepository<AdminShop, Long> {
}
