package com.example.study.api.mapper;

import com.example.study.api.dto.UserResponseDTO;
import com.example.study.domain.entity.User;

public class UserMapper {

    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getDateTime()
        );
    }
}
