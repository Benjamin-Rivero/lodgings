package fr.hb.icicafaitduspringavecboot.repository;

import fr.hb.icicafaitduspringavecboot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query("SELECT r from Room r ORDER BY RAND() LIMIT 1")
    Room getOneRandom();
}
