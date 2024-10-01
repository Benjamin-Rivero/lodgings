package fr.hb.icicafaitduspringavecboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.icicafaitduspringavecboot.dto.BookingDto;
import fr.hb.icicafaitduspringavecboot.entity.Booking;
import fr.hb.icicafaitduspringavecboot.jsonviews.JsonViewBooking;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.hb.icicafaitduspringavecboot.service.BookingService;

import java.security.Principal;


@AllArgsConstructor
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @JsonView(JsonViewBooking.BookingShowView.class)
    public Booking create(@Valid @RequestBody BookingDto bookingDto, Principal principal){
        return bookingService.createBooking(bookingDto,principal);
    }

}