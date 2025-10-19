package ru.practicum.shareit.service.booking;

import org.springframework.data.domain.Sort;
import ru.practicum.shareit.dto.booking.BookingCreateDto;
import ru.practicum.shareit.dto.booking.BookingDto;
import ru.practicum.shareit.dto.booking.BookingUpdateDto;

import java.util.List;

public interface BookingService {
    BookingDto create(BookingCreateDto dto, Long itemId, Long userId);

    BookingDto update(BookingUpdateDto dto, Long id);

    void delete(Long id);

    BookingDto findById(Long id);

    List<BookingDto> findAllByUserId(Long userId, int from, int size, Sort.Direction direction);

    List<BookingDto> findAllByItemId(Long itemId, int from, int size, Sort.Direction direction);


}
