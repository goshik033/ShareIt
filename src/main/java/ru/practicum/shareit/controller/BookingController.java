package ru.practicum.shareit.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.dto.booking.BookingCreateDto;
import ru.practicum.shareit.dto.booking.BookingDto;
import ru.practicum.shareit.dto.booking.BookingUpdateDto;
import ru.practicum.shareit.service.booking.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;


    @GetMapping("/bookings/{id}")
    public BookingDto getById(@PathVariable @Positive Long id) {
        return bookingService.findById(id);
    }

    @PatchMapping("/bookings/{id}")
    public BookingDto update(@PathVariable @Positive Long id,
                             @RequestBody @Valid BookingUpdateDto dto) {
        return bookingService.update(dto, id);
    }

    @DeleteMapping("/bookings/{id}")
    public void delete(@PathVariable @Positive Long id) {
        bookingService.delete(id);
    }


    @PostMapping("/items/{itemId}/bookings")
    public BookingDto create(@RequestHeader("X-Sharer-User-Id") @Positive Long userId,
                             @PathVariable @Positive Long itemId,
                             @RequestBody @Valid BookingCreateDto dto) {
        return bookingService.create(dto, itemId, userId);
    }

    @GetMapping("/bookings")
    public List<BookingDto> listMyBookings(
            @RequestHeader("X-Sharer-User-Id") @Positive Long userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        return bookingService.findAllByUserId(userId, from, size, direction);
    }

    @GetMapping("/users/{userId}/bookings")
    public List<BookingDto> listByUser(
            @PathVariable @Positive Long userId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        return bookingService.findAllByUserId(userId, from, size, direction);
    }

    @GetMapping("/items/{itemId}/bookings")
    public List<BookingDto> listByItem(
            @PathVariable @Positive Long itemId,
            @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
            @RequestParam(defaultValue = "10") @Positive Integer size,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        return bookingService.findAllByItemId(itemId, from, size, direction);
    }
}