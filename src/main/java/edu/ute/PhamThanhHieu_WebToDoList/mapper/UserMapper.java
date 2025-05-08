package edu.ute.PhamThanhHieu_WebToDoList.mapper;

import org.mapstruct.Mapper;

import edu.ute.PhamThanhHieu_WebToDoList.dto.UserRequestDTO;
import edu.ute.PhamThanhHieu_WebToDoList.dto.UserResponseDTO;
import edu.ute.PhamThanhHieu_WebToDoList.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDTO dto);
    UserResponseDTO toDto(User user);
}

