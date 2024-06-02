package mono.ecommerce.order.controller;

import lombok.Getter;
import lombok.Setter;
import mono.ecommerce.order.domain.OrderStatus;

@Getter
@Setter
public class OrderUpdate {
    private String status;

    public OrderStatus getOrderStatus() {
        for(OrderStatus orderStatus : OrderStatus.values()) {
            if(orderStatus.getValue().equals(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid order status value: " + status);
    }
}
