package br.com.giovani.school.service.impl;

import br.com.giovani.school.mapper.UserMapper;
import br.com.giovani.school.model.User;
import br.com.giovani.school.model.dto.UserRequestDTO;
import br.com.giovani.school.model.dto.UserResponseDTO;
import br.com.giovani.school.repository.UserRepository;
import br.com.giovani.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = repository.findAll();
        List<UserResponseDTO> response = new ArrayList<>();

        users.forEach(user -> {
            response.add(mapper.toDTO(user));
        });

        return response;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("User %s not found", id)));

        return mapper.toDTO(user);
    }

    @Override
    public UserResponseDTO create(UserRequestDTO userRequest) {
        Optional<User> user = repository.findByEmailContaining(userRequest.getEmail());

        if (user.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    String.format("User with email %s already exists", userRequest.getEmail()));
        }

        return mapper.toDTO(repository.save(mapper.toModel(userRequest)));
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userRequest) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("User %s not found", id)
                ));

        return mapper.toDTO(
                repository.save(User.builder()
                        .id(id)
                        .name(userRequest.getName())
                        .email(userRequest.getEmail()).build()));
    }

    @Override
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format("User %s not found", id)));

        repository.deleteById(id);
    }
}
