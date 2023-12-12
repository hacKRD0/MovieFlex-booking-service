package com.movie.booking.system.service.enums;

public enum BookingStatus {

    PENDING, // Initial status when a booking request is submitted
    CANCELLED, // Booking Request gets cancelled and the reserved seats are released
    CONFIRMED, // Payment successful and tickets are issued -> Notification Service to trigger ticket details
    REFUND // Booked was cancelled and refund has to be initiated
}
