package ru.practicum.shareit.service.item;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.practicum.shareit.dto.item.ItemCreateDto;
import ru.practicum.shareit.dto.item.ItemDto;
import ru.practicum.shareit.dto.item.ItemUpdateDto;
import ru.practicum.shareit.mapper.ItemMapper;
import ru.practicum.shareit.model.Item;
import ru.practicum.shareit.model.User;
import ru.practicum.shareit.repository.ItemRepository;
import ru.practicum.shareit.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(ItemCreateDto dto, Long userId) {
        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User не найден: " + userId));
        Item saved = itemRepository.save(ItemMapper.toEntity(dto, owner));
        return ItemMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ItemDto update(ItemUpdateDto dto, Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item не найден: " + id));
        if (dto.getDescription() != null) {
            item.setDescription(dto.getDescription());
        }
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        if (dto.getAvailable() != null) {
            item.setAvailable(dto.getAvailable());
        }

        Item saved = itemRepository.save(item);
        return ItemMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item не найден: " + id));
        itemRepository.deleteById(id);
    }

    @Override
    public ItemDto findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item не найден: " + id));
        return ItemMapper.toDto(item);
    }

    @Override
    public List<ItemDto> findAll() {
        return itemRepository.findAll().stream().map(ItemMapper::toDto).toList();
    }

    public List<ItemDto> findAllByOwnerId(Long id, int from, int size) {
        int page = from / size;
        Pageable p = PageRequest.of(page, size, Sort.by("id").ascending());
        return itemRepository.findAllByOwnerId(id, p).stream().map(ItemMapper::toDto).toList();
    }
}
