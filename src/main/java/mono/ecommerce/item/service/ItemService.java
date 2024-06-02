package mono.ecommerce.item.service;

import mono.ecommerce.item.controller.ItemDto;
import mono.ecommerce.item.domain.Item;
import mono.ecommerce.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDto> findAll() {
        return this.itemRepository.findAll().stream().map(Item::toDto).toList();
    }
}
