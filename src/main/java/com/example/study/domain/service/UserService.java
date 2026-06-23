package com.example.study.domain.service;

import com.example.study.api.dto.UserRequestDTO;
import com.example.study.api.dto.UserResponseDTO;
import com.example.study.api.mapper.UserMapper;
import com.example.study.domain.entity.User;
import com.example.study.domain.enums.UserRole;
import com.example.study.domain.exception.BusinessException;
import com.example.study.domain.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserResponseDTO register(UserRequestDTO dto){
        boolean exists = userRepository.existsByLogin(dto.getLogin());
        if(exists){
            throw new BusinessException("There is already a user with that name!");
        }

        String cleanPassword = dto.getPassword();
        String encryptedPassword = passwordEncoder.encode(cleanPassword);

        User user = new User();
        user.setLogin(dto.getLogin());
        user.setPassword(encryptedPassword);
        user.setUserRole(UserRole.USER);

        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDTO> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }


}
