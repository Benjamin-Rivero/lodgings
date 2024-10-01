package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.entity.Favorite;
import fr.hb.icicafaitduspringavecboot.entity.FavoriteId;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.FavoriteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    public boolean createOrDelete(FavoriteId favoriteId){
        if(favoriteRepository.existsById(favoriteId)){
            favoriteRepository.deleteById(favoriteId);
            return !favoriteRepository.existsById(favoriteId);
		} else {
            favoriteRepository.saveAndFlush(toEntity(favoriteId));
            return true;
		}

	}

    private Favorite toEntity(FavoriteId favoriteId) {
        Favorite favorite = new Favorite();
        Lodging lodging = lodgingService.findById(favoriteId.getLodgingId());
        User user = userService.findById(favoriteId.getUserId());
        favorite.setId(favoriteId);
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
