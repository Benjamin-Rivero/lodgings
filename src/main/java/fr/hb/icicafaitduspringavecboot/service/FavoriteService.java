package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.FavoriteDto;
import fr.hb.icicafaitduspringavecboot.entity.Favorite;
import fr.hb.icicafaitduspringavecboot.entity.FavoriteId;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.FavoriteRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    public Favorite create(FavoriteDto favoriteDto){
        return favoriteRepository.saveAndFlush(toEntity(favoriteDto));
    }

    private Favorite toEntity(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();
        Lodging lodging = lodgingService.findById(favoriteDto.getIds().getLodgingId());
        User user = userService.findById(favoriteDto.getIds().getUserId());
        favorite.setId(favoriteDto.getIds());
        favorite.setLodging(lodging);
        favorite.setUser(user);
        return favorite;
    }


    public void delete(Favorite object) {
        if(object!=null) favoriteRepository.delete(object);
    }


    public Favorite findById(FavoriteId id) {
        return favoriteRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
