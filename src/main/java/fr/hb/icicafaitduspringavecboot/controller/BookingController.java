package fr.hb.icicafaitduspringavecboot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.hb.icicafaitduspringavecboot.service.BookingService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

}