package ru.practicum.shareit.mapper;

import ru.practicum.shareit.dto.feedback.FeedbackCreateDto;
import ru.practicum.shareit.dto.feedback.FeedbackDto;
import ru.practicum.shareit.model.Feedback;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;

public final class FeedbackMapper {
    private FeedbackMapper() {
    }

    public static FeedbackDto toDto(Feedback feedback) {
        return FeedbackDto.builder()
                .id(feedback.getId())
                .description(feedback.getDescription())
                .created(feedback.getCreated())
                .itemId(feedback.getItem().getId())
                .userId(feedback.getUser().getId())
                .rating(feedback.getRating())
                .build();
    }

    public static Feedback toEntity(FeedbackCreateDto dto, User user, Item item) {
        return Feedback.builder()
                .description(dto.getDescription())
                .item(item)
                .user(user)
                .rating(dto.getRating())
                .build();
    }
}
