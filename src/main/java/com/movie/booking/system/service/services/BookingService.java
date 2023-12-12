package com.movie.booking.system.service.services;

import com.movie.booking.system.service.dtos.BookingDTO;
import com.movie.booking.system.service.dtos.ResponseDTO;

public interface BookingService {

    public BookingDTO createBooking(BookingDTO bookingDTO);
}
