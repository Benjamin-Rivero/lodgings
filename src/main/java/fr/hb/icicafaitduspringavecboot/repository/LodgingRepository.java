package fr.hb.icicafaitduspringavecboot.repository;

import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LodgingRepository  extends JpaRepository<Lodging,String> {
}
