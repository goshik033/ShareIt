package ru.practicum.shareit.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.model.Feedback;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByUserId(Long userId, Pageable pageable);

    List<Feedback> findAllByItemId(Long itemId, Pageable pageable);
}
