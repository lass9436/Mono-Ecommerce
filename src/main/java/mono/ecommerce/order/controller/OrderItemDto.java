package mono.ecommerce.order.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemDto {
    private String itemName;
    private Long quantity;
    private Long totalPrice;
}
