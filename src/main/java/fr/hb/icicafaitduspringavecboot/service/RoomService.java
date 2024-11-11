package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.RoomDto;
import fr.hb.icicafaitduspringavecboot.entity.Room;
import fr.hb.icicafaitduspringavecboot.repository.RoomRepository;
import fr.hb.icicafaitduspringavecboot.utils.Slugger;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoomService{

    private final RoomRepository roomRepository;
    private final Slugger slugger;

    public Room create(RoomDto object) {
        return roomRepository.save(toEntity(object));
    }

    private Room toEntity(RoomDto object) {
        Room room = new Room();
        room.setType(object.getType());
        room.setTranslationKey(String.format("room:%s",slugger.slugify(object.getType())));
        return room;
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Room> list() {
        return roomRepository.findAll();
    }

}
