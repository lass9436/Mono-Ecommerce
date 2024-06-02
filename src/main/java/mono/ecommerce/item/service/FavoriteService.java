package mono.ecommerce.item.service;

import lombok.RequiredArgsConstructor;
import mono.ecommerce.item.controller.FavoriteDto;
import mono.ecommerce.item.domain.Favorite;
import mono.ecommerce.item.domain.Item;
import mono.ecommerce.item.repository.FavoriteRepository;
import mono.ecommerce.item.repository.ItemRepository;
import mono.ecommerce.user.domain.User;
import mono.ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<FavoriteDto> findByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId).stream().map(Favorite::toDto).toList();
    }

    @Transactional
    public FavoriteDto registerFavorite(Long id, Long itemId) {
        final User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found"));
        final Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("item not found"));
        boolean isAlreadyFavorite = favoriteRepository.existsByUserIdAndItemId(user.getId(), item.getId());
        if (isAlreadyFavorite) {throw new IllegalArgumentException("item already exists in favorite list");}
        final Favorite favorite = new Favorite(user, item);
        return favoriteRepository.save(favorite).toDto();
    }
}
