package mono.ecommerce.order.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDto> orderItems;
    private Long orderPrice;
}
