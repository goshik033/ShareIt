package ru.practicum.shareit.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.dto.feedback.FeedbackCreateDto;
import ru.practicum.shareit.dto.feedback.FeedbackDto;
import ru.practicum.shareit.dto.feedback.FeedbackUpdateDto;
import ru.practicum.shareit.model.FeedbackSortBy;
import ru.practicum.shareit.service.feedback.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
@Validated
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/{id}")
    public FeedbackDto getById(@PathVariable @Positive Long id) {
        return feedbackService.findById(id);
    }

    @GetMapping
    public List<FeedbackDto> list(
            @RequestParam(required = false) @Positive Long userId,
            @RequestParam(required = false) @Positive Long itemId,
            @RequestParam(defaultValue = "0") @PositiveOrZero int from,
            @RequestParam(defaultValue = "10") @Positive int size,
            @RequestParam(defaultValue = "CREATED") FeedbackSortBy sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        if ((userId == null && itemId == null) || (userId != null && itemId != null)) {
            throw new IllegalArgumentException("Передай либо userId, либо itemId (но не оба сразу).");
        }

        if (userId != null) {
            return feedbackService.findAllByUserId(userId, from, size, sortBy, direction);
        } else {
            return feedbackService.findAllByItemId(itemId, from, size, sortBy, direction);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackDto create(@RequestBody @Valid FeedbackCreateDto dto,
                              @RequestParam @Positive Long userId,
                              @RequestParam @Positive Long itemId) {
        return feedbackService.create(dto, userId, itemId);
    }

    @PatchMapping("/{id}")
    public FeedbackDto update(@RequestBody @Valid FeedbackUpdateDto dto,
                              @PathVariable @Positive Long id) {
        return feedbackService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void delete(@PathVariable @Positive Long id) {
        feedbackService.delete(id);
    }

}
