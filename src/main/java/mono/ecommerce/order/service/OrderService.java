package mono.ecommerce.order.service;

import mono.ecommerce.order.controller.OrderDto;
import mono.ecommerce.order.domain.Order;
import mono.ecommerce.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public List<OrderDto> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(Order::toDto).toList();
    }
}
