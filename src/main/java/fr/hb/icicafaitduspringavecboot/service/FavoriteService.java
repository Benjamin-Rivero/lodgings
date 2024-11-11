package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.FavoriteDto;
import fr.hb.icicafaitduspringavecboot.entity.Favorite;
import fr.hb.icicafaitduspringavecboot.entity.FavoriteId;
import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.FavoriteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    public boolean createOrDelete(FavoriteDto favoriteDto, Principal principal){
        User user = userService.findByEmail(principal.getName());
        FavoriteId favoriteId = new FavoriteId(user.getId(),favoriteDto.getLodgingId());
        System.out.println(favoriteRepository.existsById(favoriteId));
        if(favoriteRepository.existsById(favoriteId)){
            favoriteRepository.deleteById(favoriteId);
            return !favoriteRepository.existsById(favoriteId);
		} else {
            System.out.println("DANS CREATE");
            favoriteRepository.saveAndFlush(toEntity(favoriteId));
            return true;
		}
	}

    public boolean create(FavoriteDto favoriteDto, Principal principal){
        try {
            User user = userService.findByEmail(principal.getName());
            FavoriteId favoriteId = new FavoriteId(user.getId(),favoriteDto.getLodgingId());
            System.out.println("DANS CREATE");
            Favorite favorite = new Favorite();
            favorite.setCreatedAt(LocalDateTime.now());
            favorite.setId(favoriteId);
            favorite.setUser(user);
            favorite.setLodging(lodgingService.findById(favoriteDto.getLodgingId()));
            favoriteRepository.saveAndFlush(favorite);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Favorite toEntity(FavoriteId favoriteId) {
        Favorite favorite = new Favorite();
        Lodging lodging = lodgingService.findById(favoriteId.getLodgingId());
        User user = userService.findById(favoriteId.getUserId());
        favorite.setCreatedAt(LocalDateTime.now());
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

    public List<Favorite> getByUser(String slug) {
        return favoriteRepository.findByUserSlug(slug);
    }
}
