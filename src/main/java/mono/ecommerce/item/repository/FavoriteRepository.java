package mono.ecommerce.item.repository;

import mono.ecommerce.item.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
    Boolean existsByUserIdAndItemId(Long userId, Long itemId);
    Long deleteByUserIdAndItemId(Long userId, Long itemId);
}
