package mono.ecommerce.order.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRegister {
    private List<OrderItemRegister> orderItems;

    @Setter
    @Getter
    public static class OrderItemRegister {
        private Long itemId;
        private Long quantity;

    }
}


