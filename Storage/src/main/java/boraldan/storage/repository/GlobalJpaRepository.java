package boraldan.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface GlobalJpaRepository<T> extends JpaRepository<T, UUID> {

    Optional<T> findByItemId(UUID itemId);

    // здесь ключевое слово In указывает на то, что метод ищет объекты, где itemId содержится в списке itemIdList.
//    List<T> findAll(List<UUID> itemIdList);   вроде тоже самое
    List<T> findAllByItemIdIn(List<UUID> itemIdList);

    void deleteByItemId(UUID itemId);


}
