package fr.hb.icicafaitduspringavecboot.repository;

import fr.hb.icicafaitduspringavecboot.entity.Favorite;
import fr.hb.icicafaitduspringavecboot.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}
