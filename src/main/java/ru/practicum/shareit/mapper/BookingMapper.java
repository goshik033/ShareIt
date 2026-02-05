package ru.practicum.shareit.mapper;

import ru.practicum.shareit.dto.booking.BookingCreateDto;
import ru.practicum.shareit.dto.booking.BookingDto;
import ru.practicum.shareit.model.Booking;
import ru.practicum.shareit.model.BookingStatus;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;

public final class BookingMapper {
    private BookingMapper() {
    }

    public static Booking toEntity(BookingCreateDto dto, User user, Item item) {
        return Booking.builder()
                .item(item)
                .user(user)
                .start(dto.getStart())
                .end(dto.getEnd())
                .status(BookingStatus.WAITING)
                .build();
    }

    public static BookingDto toDto(Booking b) {
        return BookingDto.builder()
                .id(b.getId())
                .itemId(b.getItem().getId())
                .userId(b.getUser().getId())
                .start(b.getStart())
                .end(b.getEnd())
                .status(b.getStatus())
                .build();
    }
}