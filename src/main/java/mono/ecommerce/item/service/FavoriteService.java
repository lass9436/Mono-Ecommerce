package mono.ecommerce.item.service;

import lombok.RequiredArgsConstructor;
import mono.ecommerce.item.controller.FavoriteDto;
import mono.ecommerce.item.domain.Favorite;
import mono.ecommerce.item.repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    public List<FavoriteDto> findByUserId(Long userId) {
        return favoriteRepository.findByUserId(userId).stream().map(Favorite::toDto).toList();
    }
}
