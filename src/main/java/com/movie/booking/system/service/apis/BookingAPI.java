package com.movie.booking.system.service.apis;

import com.movie.booking.system.service.dtos.BookingDTO;
import com.movie.booking.system.service.dtos.ResponseDTO;
import com.movie.booking.system.service.services.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bookings")
@Slf4j
public class BookingAPI {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO createBooking(@Valid @RequestBody BookingDTO bookingDTO){
        log.info("Entered into BookingAPI with json request" + bookingDTO);
        return this.bookingService.createBooking(bookingDTO);
    }
}
