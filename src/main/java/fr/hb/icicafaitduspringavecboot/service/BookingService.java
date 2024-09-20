package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.entity.Booking;
import fr.hb.icicafaitduspringavecboot.repository.BookingRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookingService implements ServiceListInterface<Booking,String> {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> list() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking create(Booking object) {
        return bookingRepository.saveAndFlush(object);
    }

    @Override
    public Booking update(Booking object, String id) {
        object.setId(id);
        bookingRepository.flush();
        return object;
    }

    @Override
    public void delete(Booking object) {
        if(object!=null) bookingRepository.delete(object);
    }

    @Override
    public Booking findById(String id) {
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
