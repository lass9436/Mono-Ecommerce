package mono.ecommerce.order.service;

import mono.ecommerce.order.controller.OrderDto;
import mono.ecommerce.order.controller.OrderUpdate;
import mono.ecommerce.order.domain.Order;
import mono.ecommerce.order.domain.OrderStatus;
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

    public OrderDto findById(Long userId, Long orderId) {
        return orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(() -> new IllegalArgumentException("order not found")).toDto();
    }

    @Transactional
    public OrderDto updateOrderStatus(Long userId, Long orderId, OrderUpdate orderUpdate) {
        OrderStatus orderStatus = orderUpdate.getOrderStatus();
        final Order order = orderRepository.findByIdAndUserId(orderId, userId).orElseThrow(() -> new IllegalArgumentException("order not found"));
        order.updateStatus(orderStatus);
        return order.toDto();
    }
}
