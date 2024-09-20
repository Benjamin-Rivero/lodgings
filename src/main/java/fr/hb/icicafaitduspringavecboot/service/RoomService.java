package fr.hb.icicafaitduspringavecboot.service;

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
public class RoomService implements ServiceListInterface<Room,Long> {

    private final RoomRepository roomRepository;

    @Override
    public Room create(Room object) {
        return roomRepository.saveAndFlush(object);
    }

    @Override
    public Room update(Room object, Long id) {
        object.setId(id);
        roomRepository.flush();
        return object;
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
}
