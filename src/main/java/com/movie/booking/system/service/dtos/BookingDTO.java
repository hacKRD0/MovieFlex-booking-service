package com.movie.booking.system.service.dtos;

import com.movie.booking.system.service.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
public class BookingDTO {
    public UUID bookingId;
    @NotBlank(message = "user id is mandatory and it cannot be null or empty")
    private String userId;
    @NotNull(message = "movie id is mandatory")
    @Positive(message = "Please provide valid movie id")
    private Integer movieId;
    @NotNull(message = "Please select seats to reserve")
    private List<String> seatsBooked;
    @NotNull(message = "Please select show date")
    private LocalDate showDate;
    @NotNull(message = "Please select show time")
    private LocalTime showTime;
    @NotNull(message = "booking amount is mandatory")
    private Double bookingAmount;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
