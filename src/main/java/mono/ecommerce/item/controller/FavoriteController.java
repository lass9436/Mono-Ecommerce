package mono.ecommerce.item.controller;

import mono.ecommerce.item.service.FavoriteService;
import mono.ecommerce.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public List<FavoriteDto> findByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return favoriteService.findByUserId(userDetails.getId());
    }

    @PostMapping("/{itemId}")
    public FavoriteDto registerFavorite(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("itemId") Long itemId) {
        return favoriteService.registerFavorite(userDetails.getId(), itemId);
    }

}
