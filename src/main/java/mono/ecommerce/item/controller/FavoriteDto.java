package mono.ecommerce.item.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavoriteDto {
    private Long favoriteId;
    private Long itemId;
    private String name;
    private Long price;
    private Long quantity;
}
