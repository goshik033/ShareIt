package ru.practicum.shareit.dto.itemRequest;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequestDto {
    private Long id;
    private Long requesterId;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
}