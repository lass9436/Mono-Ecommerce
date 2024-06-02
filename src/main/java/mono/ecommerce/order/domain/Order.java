package mono.ecommerce.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mono.ecommerce.order.controller.OrderDto;
import mono.ecommerce.order.controller.OrderItemDto;
import mono.ecommerce.user.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public OrderDto toDto(){
        List<OrderItemDto> orderItemDtoList = orderItems.stream().map(OrderItem::toDto).toList();
        Long orderPrice = orderItemDtoList.stream().mapToLong(OrderItemDto::getTotalPrice).sum();
        return new OrderDto(orderDate, status.getValue(), orderItemDtoList, orderPrice);
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.status = orderStatus;
    }
}
