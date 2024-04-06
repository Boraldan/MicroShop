package boraldan.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PriceUnifiedRepo<T> extends JpaRepository<T, Long> {
}
