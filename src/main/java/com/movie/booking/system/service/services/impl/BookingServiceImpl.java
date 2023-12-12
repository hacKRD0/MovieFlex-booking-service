package com.movie.booking.system.service.services.impl;

import com.movie.booking.system.service.brokers.PaymentServiceBroker;
import com.movie.booking.system.service.dtos.BookingDTO;
import com.movie.booking.system.service.dtos.ResponseDTO;
import com.movie.booking.system.service.entities.BookingEntity;
import com.movie.booking.system.service.enums.BookingStatus;
import com.movie.booking.system.service.repositories.BookingRepository;
import com.movie.booking.system.service.services.BookingService;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentServiceBroker paymentService;

    @Override
    @Transactional
    public BookingDTO createBooking(BookingDTO bookingDTO){
        log.info("Entered into BookingServiceImpl class with request : "+ bookingDTO.toString());
        BookingEntity bookingEntity = BookingEntity.builder()
                .userId(bookingDTO.getUserId())
                .bookingStatus(BookingStatus.PENDING)
                .seatsBooked(bookingDTO.getSeatsBooked())
                .bookingAmount(bookingDTO.getBookingAmount())
                .bookingId(bookingDTO.getBookingId())
                .showDate(bookingDTO.getShowDate())
                .showTime(bookingDTO.getShowTime())
                .movieId(bookingDTO.getMovieId())
                .build();
        this.bookingRepository.save(bookingEntity);

        bookingDTO.setBookingId(bookingEntity.getBookingId());
        bookingDTO.setBookingStatus(BookingStatus.PENDING);

        //call payment service
        log.info("Calling payment service to do the payment for the amount : "+ bookingEntity.getBookingAmount());
//        try {
            BookingDTO paymentResponse = this.paymentService.processPayment(bookingDTO);
//        } catch() {
//            log.info("");
//        }
        log.info("Payment was successful for the booking id : "+ bookingEntity.getBookingId());
        bookingEntity.setBookingStatus(paymentResponse.getBookingStatus());
        return BookingDTO.builder()
                        .bookingId(bookingEntity.getBookingId())
                        .userId(bookingEntity.getUserId())
                        .bookingStatus(bookingEntity.getBookingStatus())
                        .movieId(bookingEntity.getMovieId())
                        .bookingAmount(bookingEntity.getBookingAmount())
                        .seatsBooked(bookingEntity.getSeatsBooked())
                        .showDate(bookingEntity.getShowDate())
                        .showTime(bookingEntity.getShowTime())
                        .build();
    };
}
