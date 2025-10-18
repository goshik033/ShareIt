package ru.practicum.shareit.dto.feedback;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    private Long id;
    private Long itemId;
    private Long userId;
    private String description;
    private LocalDateTime created;
    private Short rating;
}
