package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.RoomDto;
import fr.hb.icicafaitduspringavecboot.entity.Room;
import fr.hb.icicafaitduspringavecboot.repository.RoomRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceInterface;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RoomService implements ServiceListInterface<Room,Long, RoomDto,RoomDto> {

    private final RoomRepository roomRepository;

    @Override
    public Room create(RoomDto object) {
        return roomRepository.saveAndFlush(toEntity(object));
    }

    private Room toEntity(RoomDto object) {
        Room room = new Room();
        room.setTranslationKey("oui");
        room.setType(object.getType());
        return room;
    }

    @Override
    public Room update(RoomDto object, Long id) {
        Room room = toEntity(object);
        room.setId(id);
        roomRepository.saveAndFlush(room);
        return room;
    }

    @Override
    public void delete(Room object) {
        if(object!=null) roomRepository.delete(object);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Room> list() {
        return roomRepository.findAll();
    }

    public Room getOneRandom(){
        return roomRepository.getOneRandom();
    }
}
