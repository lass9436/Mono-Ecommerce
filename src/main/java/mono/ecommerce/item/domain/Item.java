package mono.ecommerce.item.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mono.ecommerce.item.controller.ItemDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long quantity;

    @Transient
    private Long orderQuantity;

    @Transient
    private Long orderPrice;

    public ItemDto toDto(){
        return new ItemDto(id, name, price, quantity);
    }

    public void order(Long quantity){
        if(this.quantity - quantity < 0) throw new IllegalArgumentException("out of stock");
        this.quantity -= quantity;
        this.orderQuantity = quantity;
        this.orderPrice = quantity * price;
    }
}
