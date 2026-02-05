package ru.practicum.shareit.service.booking;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.dto.booking.BookingCreateDto;
import ru.practicum.shareit.dto.booking.BookingDto;
import ru.practicum.shareit.dto.booking.BookingUpdateDto;
import ru.practicum.shareit.mapper.BookingMapper;
import ru.practicum.shareit.model.Booking;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;
import ru.practicum.shareit.repository.BookingRepository;
import ru.practicum.shareit.repository.ItemRepository;
import ru.practicum.shareit.repository.UserRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public BookingDto create(BookingCreateDto dto, Long itemId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User не найден: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item не найден: " + itemId));
        Booking saved = bookingRepository.save(BookingMapper.toEntity(dto, user, item));

        return BookingMapper.toDto(saved);

    }


    @Transactional
    public BookingDto update(BookingUpdateDto dto, Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking не найден: " + id));

        if (dto.getStatus() != null) {
            booking.setStatus(dto.getStatus());
        }
        if (dto.getStart() != null) {
            booking.setStart(dto.getStart());
        }
        if (dto.getEnd() != null) {
            booking.setEnd(dto.getEnd());
        }
        Booking saved = bookingRepository.save(booking);

        return BookingMapper.toDto(saved);

    }

    public BookingDto findById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking не найден: " + id));
        return BookingMapper.toDto(booking);
    }
    @Transactional
    public void delete(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new EntityNotFoundException("Booking не найден: " + id);
        }
        bookingRepository.deleteById(id);
    }

    public List<BookingDto> findAllByUserId(Long userId, int from, int size, Sort.Direction direction) {

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User не найден: " + userId);
        }

        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction == null ? Sort.Direction.DESC : direction, "start"));

        return bookingRepository.findAllByUserId(userId, pageable)
                .stream()
                .map(BookingMapper::toDto)
                .toList();
    }

    public List<BookingDto> findAllByItemId(Long itemId, int from, int size, Sort.Direction direction) {

        if (!itemRepository.existsById(itemId)) {
            throw new EntityNotFoundException("Item не найден: " + itemId);
        }

        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction == null ? Sort.Direction.DESC : direction, "start"));

        return bookingRepository.findAllByItemId(itemId, pageable)
                .stream()
                .map(BookingMapper::toDto)
                .toList();
    }


}
