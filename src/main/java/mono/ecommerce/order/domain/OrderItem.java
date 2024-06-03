package mono.ecommerce.order.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mono.ecommerce.item.domain.Item;
import mono.ecommerce.order.controller.OrderItemDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long totalPrice;

    public OrderItem(Order order, Item item) {
        this.order = order;
        this.item = item;
        this.quantity = item.getOrderQuantity();
        this.totalPrice = item.getOrderPrice();
    }

    public OrderItemDto toDto() {
        return new OrderItemDto(item.getName(), quantity, totalPrice);
    }
}
