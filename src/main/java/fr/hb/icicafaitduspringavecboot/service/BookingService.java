package fr.hb.icicafaitduspringavecboot.service;

import fr.hb.icicafaitduspringavecboot.dto.BookingDto;
import fr.hb.icicafaitduspringavecboot.entity.Booking;
import fr.hb.icicafaitduspringavecboot.entity.User;
import fr.hb.icicafaitduspringavecboot.repository.BookingRepository;
import fr.hb.icicafaitduspringavecboot.repository.UserRepository;
import fr.hb.icicafaitduspringavecboot.service.interfaces.ServiceListInterface;
import fr.hb.icicafaitduspringavecboot.utils.StringUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class BookingService implements ServiceListInterface<Booking,String,BookingDto, BookingDto> {

    private final BookingRepository bookingRepository;
    private final LodgingService lodgingService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public List<Booking> list() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking create(BookingDto object) {
        return bookingRepository.save(toEntity(object));
    }

    public Booking createBooking(BookingDto bookingDto, Principal principal){
        Booking booking = create(bookingDto);
        User user = userService.findByEmail(principal.getName());
        user.getBookings().add(booking);
        userRepository.saveAndFlush(user);
        booking.setUser(user);
        return bookingRepository.saveAndFlush(booking);
    }

    private Booking toEntity(BookingDto object) {
        Booking booking = new Booking();
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStartedAt(object.getStartedAt());
        booking.setFinishedAt(object.getStartedAt().plusDays(object.getNumberOfDays()));
        booking.setNumber(StringUtil.generateRandomString(8));
        booking.setQuantity(object.getQuantity());
        booking.setLodging(lodgingService.findById(object.getLodgingId()));
        if(object.getUserId()!=null) booking.setUser(userService.findById(object.getUserId()));
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
