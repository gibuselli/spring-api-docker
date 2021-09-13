package br.com.giovani.school.service;

import br.com.giovani.school.model.dto.UserRequestDTO;
import br.com.giovani.school.model.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO create(UserRequestDTO userRequest);

    UserResponseDTO update(Long id, UserRequestDTO userRequest);

    void delete(Long id);
}
