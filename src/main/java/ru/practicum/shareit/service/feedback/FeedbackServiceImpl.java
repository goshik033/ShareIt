package ru.practicum.shareit.service.feedback;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.dto.feedback.FeedbackCreateDto;
import ru.practicum.shareit.dto.feedback.FeedbackDto;
import ru.practicum.shareit.dto.feedback.FeedbackUpdateDto;
import ru.practicum.shareit.mapper.FeedbackMapper;
import ru.practicum.shareit.model.Feedback;
import ru.practicum.shareit.model.FeedbackSortBy;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;
import ru.practicum.shareit.repository.FeedbackRepository;
import ru.practicum.shareit.repository.ItemRepository;
import ru.practicum.shareit.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public FeedbackDto findById(Long id) {
        Feedback feedback = feedbackRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Feedback не найден: " + id));
        return FeedbackMapper.toDto(feedback);
    }

    @Override
    public List<FeedbackDto> findAllByUserId(Long userId, int from, int size, FeedbackSortBy sortBy,
                                             Sort.Direction direction) {
        int page = from / size;

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User не найден: " + userId);
        }

        Pageable pageable = PageRequest.of(page, size, buildSort(sortBy, direction));

        return feedbackRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(FeedbackMapper::toDto)
                .toList();
    }

    @Override
    public List<FeedbackDto> findAllByItemId(Long itemId, int from, int size, FeedbackSortBy sortBy,
                                             Sort.Direction direction) {
        if (!itemRepository.existsById(itemId)) {
            throw new EntityNotFoundException("Item не найден: " + itemId);
        }
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, buildSort(sortBy, direction));
        return feedbackRepository.findAllByItemId(itemId, pageable)
                .stream()
                .map(FeedbackMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public FeedbackDto create(FeedbackCreateDto dto, Long userId, Long itemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User не найден: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item не найден: " + itemId));

        Feedback saved = feedbackRepository.save(FeedbackMapper.toEntity(dto, user, item));

        return FeedbackMapper.toDto(saved);
    }

    @Override
    @Transactional
    public FeedbackDto update(Long id, FeedbackUpdateDto dto) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Feedback не найден: " + id));

        if (dto.getDescription() != null) {
            feedback.setDescription(dto.getDescription());
        }
        if (dto.getRating() != null) {
            feedback.setRating(dto.getRating());
        }

        Feedback saved = feedbackRepository.save(feedback);

        return FeedbackMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new EntityNotFoundException("Feedback не найден: " + id);
        }
        feedbackRepository.deleteById(id);
    }


    private Sort buildSort(FeedbackSortBy sortBy, Sort.Direction direction) {
        Sort.Direction dir = direction != null ? direction : Sort.Direction.DESC;
        String primary = (sortBy == FeedbackSortBy.RATING) ? "rating" : "created";
        return Sort.by(dir, primary);
    }


}
