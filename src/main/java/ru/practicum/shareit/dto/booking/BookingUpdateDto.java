package ru.practicum.shareit.dto.booking;

import lombok.*;
import ru.practicum.shareit.model.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingUpdateDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;
}
