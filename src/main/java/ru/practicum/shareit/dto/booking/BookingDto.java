package ru.practicum.shareit.dto.booking;

import lombok.*;
import ru.practicum.shareit.model.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private Long itemId;
    private Long userId;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
}
