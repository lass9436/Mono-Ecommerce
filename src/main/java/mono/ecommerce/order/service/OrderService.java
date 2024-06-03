package mono.ecommerce.order.service;

import lombok.RequiredArgsConstructor;
import mono.ecommerce.item.domain.Item;
import mono.ecommerce.item.repository.ItemRepository;
import mono.ecommerce.order.controller.OrderDto;
import mono.ecommerce.order.controller.OrderRegister;
import mono.ecommerce.order.controller.OrderUpdate;
import mono.ecommerce.order.domain.Order;
import mono.ecommerce.order.domain.OrderItem;
import mono.ecommerce.order.domain.OrderStatus;
import mono.ecommerce.order.repository.OrderRepository;
import mono.ecommerce.user.domain.User;
import mono.ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

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

    @Transactional
    public OrderDto registerOrder(Long userId, OrderRegister orderRegister) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user not found"));
        final Order order = new Order(user);
        final long[] totalPrice = {0L};
        final List<Item> items = orderRegister.getOrderItems().stream().map(
                item -> {
                    Item itemEntity = itemRepository.findById(item.getItemId()).orElseThrow(() -> new IllegalArgumentException("item not found"));
                    itemEntity.order(item.getQuantity());
                    totalPrice[0] += itemEntity.getOrderPrice();
                    return itemEntity;
                })
                .toList();
        final List<OrderItem> orderItems = items.stream().map(item -> new OrderItem(order, item)).toList();
        order.addOrderItems(orderItems);
        user.payment(totalPrice[0]);
        return orderRepository.save(order).toDto();
    }
}
