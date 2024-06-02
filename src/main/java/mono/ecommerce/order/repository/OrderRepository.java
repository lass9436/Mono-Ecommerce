package mono.ecommerce.order.repository;

import mono.ecommerce.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}
