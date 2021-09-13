package br.com.giovani.school.mapper;


import br.com.giovani.school.model.User;
import br.com.giovani.school.model.dto.UserRequestDTO;
import br.com.giovani.school.model.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDTO(User user);

    User toModel(UserRequestDTO request);
}
