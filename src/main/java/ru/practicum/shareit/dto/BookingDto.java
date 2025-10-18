package ru.practicum.shareit.dto;

import ru.practicum.shareit.model.BookingStatus;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;

import java.time.LocalDateTime;

public class BookingDto {
    private Long id;
    private Item item;
    private User user;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;


}
