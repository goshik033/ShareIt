package ru.practicum.shareit.service.feedback;

import org.springframework.data.domain.Sort;
import ru.practicum.shareit.dto.feedback.FeedbackCreateDto;
import ru.practicum.shareit.dto.feedback.FeedbackDto;
import ru.practicum.shareit.dto.feedback.FeedbackUpdateDto;
import ru.practicum.shareit.model.FeedbackSortBy;

import java.util.List;

public interface FeedbackService {
    FeedbackDto findById(Long id);

    List<FeedbackDto> findAllByUserId(Long userId, int from, int size, FeedbackSortBy sortBy,
                                      Sort.Direction direction);

    List<FeedbackDto> findAllByItemId(Long itemId, int from, int size, FeedbackSortBy sortBy,
                                      Sort.Direction direction);

    FeedbackDto create(FeedbackCreateDto dto, Long userId, Long itemId);

    FeedbackDto update(Long id, FeedbackUpdateDto dto);

    void delete(Long id);
}
