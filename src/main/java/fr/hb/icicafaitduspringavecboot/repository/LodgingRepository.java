package fr.hb.icicafaitduspringavecboot.repository;

import fr.hb.icicafaitduspringavecboot.entity.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SSLSession;

@Repository
public interface LodgingRepository  extends JpaRepository<Lodging,String> {
    @Query("SELECT l FROM Lodging l ORDER BY RAND() LIMIT 1")
    Lodging getOneRandom();
}
