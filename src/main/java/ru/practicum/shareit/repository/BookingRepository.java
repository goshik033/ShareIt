package ru.practicum.shareit.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.dto.booking.BookingDto;
import ru.practicum.shareit.model.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long userId, Pageable pageable);
    List<Booking> findAllByItemId(Long ItemId, Pageable pageable);
}
