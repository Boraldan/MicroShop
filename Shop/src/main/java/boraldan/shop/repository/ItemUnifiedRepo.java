package boraldan.shop.repository;

import boraldan.entitymicro.shop.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface ItemUnifiedRepo<T extends Item> extends JpaRepository<T, UUID> {

}
