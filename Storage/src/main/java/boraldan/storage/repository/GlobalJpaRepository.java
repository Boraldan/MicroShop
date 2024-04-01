package boraldan.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface GlobalJpaRepository<T> extends JpaRepository<T, UUID> {
}