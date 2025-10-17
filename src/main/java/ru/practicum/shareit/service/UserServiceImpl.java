package ru.practicum.shareit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.dto.UserCreateDto;
import ru.practicum.shareit.dto.UserDto;
import ru.practicum.shareit.dto.UserUpdateDto;
import ru.practicum.shareit.mapper.UserMapper;
import ru.practicum.shareit.model.User;
import ru.practicum.shareit.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Override
    @Transactional
    public UserDto create(UserCreateDto dto) {
        if (repo.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new DataIntegrityViolationException("Email уже используется: " + dto.getEmail());
        }
        User saved = repo.save(UserMapper.toEntity(dto));
        return UserMapper.toDto(saved);
    }

    @Override
    public UserDto getById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return repo.findAll(pageable).map(UserMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserUpdateDto dto) {
        User user = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            if (repo.existsByEmailIgnoreCase(dto.getEmail())
                    && !dto.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new DataIntegrityViolationException("Email уже используется: " + dto.getEmail());
            }
            user.setEmail(dto.getEmail());
        }

        return UserMapper.toDto(repo.save(user));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        repo.deleteById(id);
    }
}