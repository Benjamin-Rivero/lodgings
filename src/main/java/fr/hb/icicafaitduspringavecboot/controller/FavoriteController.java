package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.FavoriteDto;
import fr.hb.icicafaitduspringavecboot.entity.Favorite;
import fr.hb.icicafaitduspringavecboot.entity.FavoriteId;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewFavorite;
import fr.hb.icicafaitduspringavecboot.service.FavoriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@AllArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping
    public boolean favorite(@RequestBody FavoriteDto favoriteDto, Principal principal){
        return favoriteService.create(favoriteDto, principal);
    }

    @GetMapping("/{user}")
    @JsonView(JsonViewFavorite.FavoriteMinimalView.class)
    public List<Favorite> favorityByUser(@PathVariable(name = "user") String slug){
        return favoriteService.getByUser(slug);
    }

}
