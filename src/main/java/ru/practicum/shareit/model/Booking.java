package ru.practicum.shareit.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"user","item"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Booking {
    private Long id;
    private Item item;
    private User user;
    private LocalDateTime start;
    private LocalDateTime end;
    private BookingStatus status;

}
