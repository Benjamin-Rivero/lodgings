package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.BookingDto;
import fr.hb.icicafaitduspringavecboot.entity.Booking;
import fr.hb.icicafaitduspringavecboot.repository.BookingRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookingService implements ServiceListInterface<Booking,String,BookingDto, BookingDto> {

    private final BookingRepository bookingRepository;
    private final LodgingService lodgingService;
    private final UserService userService;

    @Override
    public List<Booking> list() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking create(BookingDto object) {
        return bookingRepository.saveAndFlush(toEntity(object));
    }

    private Booking toEntity(BookingDto object) {
        Booking booking = new Booking();
        booking.setFinishedAt(object.getFinishedAt());
        booking.setStartedAt(object.getStartedAt());
        booking.setNumber(object.getNumber());
        booking.setQuantity(object.getQuantity());
        booking.setLodging(lodgingService.findById(object.getLodgingId()));
        booking.setUser(userService.findById(object.getUserId()));
        return booking;
    }

    @Override
    public Booking update(BookingDto object, String id) {
        Booking booking = toEntity(object);
        booking.setId(id);
        bookingRepository.saveAndFlush(booking);
        return booking;
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
