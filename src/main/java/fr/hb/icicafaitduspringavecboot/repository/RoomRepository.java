package fr.hb.icicafaitduspringavecboot.repository;

import fr.hb.icicafaitduspringavecboot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
