package com.movie.booking.system.service.repositories;

import com.movie.booking.system.service.entities.BookingEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookingRepository extends CrudRepository<BookingEntity, UUID> {
}
